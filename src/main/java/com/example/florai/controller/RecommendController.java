package com.example.florai.controller;

import com.example.florai.dto.AIPickRequest;
import com.example.florai.dto.RecommendRequest;
import com.example.florai.entity.Flower;
import com.example.florai.repository.FlowerRepository;
import com.example.florai.service.AiServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

    private final AiServiceClient aiServiceClient;
    private final FlowerRepository flowerRepository;

    @Autowired
    public RecommendController(AiServiceClient aiServiceClient, FlowerRepository flowerRepository) {
        this.aiServiceClient = aiServiceClient;
        this.flowerRepository = flowerRepository;
    }

    @PostMapping
    public ResponseEntity<Map<String, Object>> handleSurvey(@RequestBody RecommendRequest request) {
        List<String> queryParams = request.getQuery();
        String[] queryArray = queryParams.toArray(new String[0]);

        // AI 서버 호출
        Map<String, Object> aiResult = aiServiceClient.getRecommendation(queryArray);

        if (aiResult == null || aiResult.containsKey("error")) {
            return ResponseEntity.badRequest().body(aiResult);
        }

        List<Map<String, Object>> indexes = (List<Map<String, Object>>) aiResult.get("indexes");

        if (indexes == null || indexes.isEmpty()) {
            return ResponseEntity.ok(Map.of(
                    "expanded_query", aiResult.getOrDefault("expanded_query", String.join(", ", queryArray)),
                    "emotion_category", aiResult.getOrDefault("emotion_category", ""),
                    "recommendations", Collections.emptyList()
            ));
        }

        // 추천된 꽃 ID 리스트 및 이유 매핑
        List<Integer> flwIdxList = indexes.stream()
                .map(entry -> ((Number) entry.get("idx")).intValue())
                .toList();

        Map<Integer, String> reasonMap = new HashMap<>();
        for (Map<String, Object> entry : indexes) {
            reasonMap.put(((Number) entry.get("idx")).intValue(), (String) entry.get("reason"));
        }

        // 꽃 DB 조회
        List<Flower> flowers = flowerRepository.findByIdIn(flwIdxList);

        // JSON으로 반환할 객체 리스트 생성
        List<AIPickRequest> resultList = new ArrayList<>();
        for (Flower flower : flowers) {
            AIPickRequest pick = new AIPickRequest();
            pick.setId(flower.getId());
            pick.setName(flower.getName());
            pick.setSpring(flower.getSpring());
            pick.setSummer(flower.getSummer());
            pick.setFall(flower.getFall());
            pick.setWinter(flower.getWinter());
            pick.setAllergy(flower.getAllergy());
            pick.setFlwLang(flower.getFlwLang());
            pick.setFlwSml(flower.getFlwSml());
            pick.setImage(flower.getFlwLmg());
            pick.setReason(reasonMap.get(flower.getId()));
            resultList.add(pick);
        }

        // JSON 형태 응답 구성
        Map<String, Object> finalResult = new HashMap<>();
        finalResult.put("expanded_query", aiResult.get("expanded_query"));
        finalResult.put("emotion_category", aiResult.get("emotion_category"));
        finalResult.put("recommendations", resultList);

        return ResponseEntity.ok(finalResult);
    }
}
