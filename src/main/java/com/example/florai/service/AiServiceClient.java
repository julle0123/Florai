package com.example.florai.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class AiServiceClient {

    private final String API_URL = "https://florai-ttyw.onrender.com/recommend";

    public Map<String, Object> getRecommendation(String[] queryArray) {
        System.out.println("프론트에서 받은 쿼리값: " + Arrays.toString(queryArray));

        // 빈 값 체크 및 기본값 대입
        boolean hasEmpty = false;
        for (String value : queryArray) {
            if (value == null || value.trim().isEmpty()) {
                hasEmpty = true;
                System.out.println("빈 값 발견됨!");
                break;
            }
        }

        if (hasEmpty) {
            System.out.println("빈 값이 있으므로 기본값으로 대체합니다.");
            if (queryArray.length >= 5) {
                if (queryArray[0] == null || queryArray[0].trim().isEmpty()) queryArray[0] = "친구";
                if (queryArray[1] == null || queryArray[1].trim().isEmpty()) queryArray[1] = "축하";
                if (queryArray[2] == null || queryArray[2].trim().isEmpty()) queryArray[2] = "생일";
                if (queryArray[3] == null || queryArray[3].trim().isEmpty()) queryArray[3] = "은은하게";
                if (queryArray[4] == null || queryArray[4].trim().isEmpty()) queryArray[4] = "은은하게";
            }
        }

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        // 요청 바디 구성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("query", queryArray);

        System.out.println("AI 서버로 보낼 requestBody: " + requestBody);

        // 요청 헤더
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // POST 요청 전송
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // 응답 파싱
                JsonNode json = objectMapper.readTree(response.getBody());
                System.out.println("AI 서버 응답 전체 JSON:\n" + json.toPrettyString());

                Map<String, Object> result = new HashMap<>();

                //  응답이 배열인 경우 (즉시 추천 리스트)
                if (json.isArray()) {
                    List<Map<String, Object>> indexes = new ArrayList<>();

                    for (JsonNode item : json) {
                        Map<String, Object> indexMap = new HashMap<>();
                        int flwIdx = item.has("FLW_IDX") ? item.path("FLW_IDX").asInt() :
                                (item.has("idx") ? item.path("idx").asInt() : 0);

                        if (flwIdx == 0) {
                            System.out.println("꽃 인덱스가 0이거나 없습니다: " + item.toPrettyString());
                            continue;
                        }

                        indexMap.put("idx", flwIdx);
                        indexMap.put("reason", item.path("reason").asText());
                        indexes.add(indexMap);
                    }

                    System.out.println("추천된 꽃 리스트 (indexes): " + indexes);
                    result.put("indexes", indexes);
                    result.put("expanded_query", String.join(" ", queryArray)); // 기본값
                    result.put("emotion_category", ""); // 기본값
                    return result;

                } else {
                    System.out.println("AI 응답이 배열이 아닙니다!");
                    return Map.of("error", "AI 응답 형식이 잘못되었습니다.");
                }

            } else {
                System.out.println(" AI 서버 응답 실패: " + response.getStatusCode());
                return Map.of("error", "AI 서버 응답 실패: " + response.getStatusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("예외 발생: " + e.getMessage());
            return Map.of("error", "예외 발생: " + e.getMessage());
        }
    }
}
