package com.example.florai.controller;

import com.example.florai.entity.Basket;
import com.example.florai.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Map;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    // 장바구니 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<Map<String, Object>>> getBasket(@PathVariable String userId) {
        List<Basket> basketList = basketService.getBasketByUserId(userId);

        List<Map<String, Object>> result = basketList.stream()
                .map(basket -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("basketIdx", basket.getBasketIdx());
                    map.put("flowerName", basket.getFlower().getName());
                    map.put("flowerPrice", basket.getFlower().getPrice());
                    map.put("flowerImg", basket.getFlower().getFlwLmg());
                    map.put("count", basket.getCnt());
                    return map;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    // 장바구니 추가
    @PostMapping
    public ResponseEntity<?> addBasket(@RequestBody Map<String, Object> body) {
        String userId = (String) body.get("userId");
        Integer flowerId = (Integer) body.get("flowerId");
        Integer count = (Integer) body.get("count");

        Basket savedBasket = basketService.addBasket(userId, flowerId, count);

        return ResponseEntity.ok(Map.of(
                "message", "장바구니 추가 성공",
                "basketId", savedBasket.getBasketIdx()
        ));
    }

    // 장바구니 삭제
    @DeleteMapping("/{basketIdx}")
    public ResponseEntity<?> deleteBasket(@PathVariable Integer basketIdx) {
        basketService.deleteBasket(basketIdx);
        return ResponseEntity.ok(Map.of("message", "장바구니 삭제 성공"));
    }
}
