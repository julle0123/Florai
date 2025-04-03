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
        System.out.println("📦 프론트에서 받은 쿼리값: " + Arrays.toString(queryArray));

        // Check for empty values
        boolean hasEmpty = false;
        for (String value : queryArray) {
            if (value == null || value.trim().isEmpty()) {
                hasEmpty = true;
                System.out.println("⚠️ 빈 값 발견됨!");
                break;
            }
        }

        if (hasEmpty) {
            System.out.println("⚠️ 빈 값이 있으므로 기본값으로 대체합니다.");
            // 기본값으로 대체 (필요시)
            if (queryArray.length >= 4) {
                if (queryArray[0] == null || queryArray[0].trim().isEmpty()) queryArray[0] = "친구";
                if (queryArray[1] == null || queryArray[1].trim().isEmpty()) queryArray[1] = "축하";
                if (queryArray[2] == null || queryArray[2].trim().isEmpty()) queryArray[2] = "생일";
                if (queryArray[3] == null || queryArray[3].trim().isEmpty()) queryArray[3] = "은은하게";
            }
        }

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper objectMapper = new ObjectMapper();

        // 요청 바디 구성
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("query", queryArray);

        System.out.println("📨 AI 서버로 보낼 requestBody: " + requestBody);

        // 요청 헤더 구성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

        try {
            // POST 요청
            ResponseEntity<String> response = restTemplate.postForEntity(API_URL, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                // 응답 본문 파싱
                JsonNode json = objectMapper.readTree(response.getBody());
                System.out.println("🧾 AI 서버 응답 전체 JSON:\n" + json.toPrettyString());

                // 결과 맵 생성
                Map<String, Object> result = new HashMap<>();

                // 확장된 쿼리 및 감정 카테고리
                String expandedQuery = json.path("expanded_query").asText();
                String emotionCategory = json.path("emotion_category").asText();

                System.out.println("🧠 확장된 쿼리: " + expandedQuery);
                System.out.println("💖 감정 카테고리: " + emotionCategory);

                result.put("expanded_query", expandedQuery);
                result.put("emotion_category", emotionCategory);

                // 추천 인덱스 파싱
                List<Map<String, Object>> indexes = new ArrayList<>();
                JsonNode indexesNode = json.path("indexes");

                if (indexesNode.isArray()) {
                    for (JsonNode item : indexesNode) {
                        Map<String, Object> indexMap = new HashMap<>();
                        // FLW_IDX 또는 idx 필드 찾기
                        int flwIdx = item.has("FLW_IDX") ? item.path("FLW_IDX").asInt() :
                                (item.has("idx") ? item.path("idx").asInt() : 0);

                        if (flwIdx == 0) {
                            System.out.println("⚠️ 꽃 인덱스가 0이거나 없습니다: " + item.toPrettyString());
                            continue;
                        }

                        indexMap.put("idx", flwIdx);
                        indexMap.put("reason", item.path("reason").asText());
                        indexes.add(indexMap);
                    }
                } else {
                    System.out.println("⚠️ indexes 필드가 배열이 아닙니다!");
                }

                System.out.println("🌸 추천된 꽃 리스트 (indexes): " + indexes);
                result.put("indexes", indexes);
                return result;

            } else {
                System.out.println("❌ AI 서버 응답 실패: " + response.getStatusCode());
                return Map.of("error", "AI 서버 응답 실패: " + response.getStatusCode());
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ 예외 발생: " + e.getMessage());
            return Map.of("error", "예외 발생: " + e.getMessage());
        }
    }
}