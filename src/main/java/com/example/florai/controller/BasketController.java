package com.example.florai.controller;

import com.example.florai.dto.BasketRequest;
import com.example.florai.dto.ProductList;
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
@RequestMapping("/Basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    // 장바구니 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<BasketRequest>> getBasket(@PathVariable String userId) {
        List<Basket> basketList = basketService.getBasketByUserId(userId);

        List<BasketRequest> result = basketList.stream()
                .map(basket -> {
                    BasketRequest dto = new BasketRequest();
                    dto.setBasketIdx(basket.getBasketIdx());
                    dto.setId(basket.getId());
                    dto.setFlowerIdx(basket.getFlower().getId());
                    dto.setFlowerName(basket.getFlower().getName());
                    dto.setFlowerPrice(basket.getFlower().getPrice());
                    dto.setFlowerImg(basket.getFlower().getFlwLmg());
                    dto.setCnt(basket.getCnt());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    // 장바구니 추가
    @PostMapping("/BasketAdd/{userId}")
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

    //
    @PatchMapping("/{basketIdx}")
    public ResponseEntity<?> updateBasketCount(@PathVariable Integer basketIdx, @RequestBody Map<String, Object> body) {
        if (!body.containsKey("count")) {
            return ResponseEntity.badRequest().body("Missing 'count' in request body");
        }

        Integer newCount = (Integer) body.get("count");
        Basket updatedBasket = basketService.updateBasketCount(basketIdx, newCount);

        return ResponseEntity.ok(Map.of(
                "message", "수량 수정 성공",
                "basketId", updatedBasket.getBasketIdx(),
                "newCount", updatedBasket.getCnt()
        ));
    }

    // 장바구니 삭제
    @DeleteMapping("/{basketIdx}")
    public ResponseEntity<?> deleteBasket(@PathVariable Integer basketIdx) {
        basketService.deleteBasket(basketIdx);
        return ResponseEntity.ok(Map.of("message", "장바구니 삭제 성공"));
    }
}
