package com.example.florai.service;

import com.example.florai.entity.Basket;
import com.example.florai.entity.Flower;
import com.example.florai.repository.BasketRepository;
import com.example.florai.repository.FlowerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BasketService {

    private final BasketRepository basketRepository;
    private final FlowerRepository flowerRepository;

    // 장바구니 조회
    public List<Basket> getBasketByUserId(String userId) {
        return basketRepository.findByUserId(userId);
    }

    // 장바구니 추가
    public Basket addBasket(String userId, Integer flowerId, Integer count) {
        Flower flower = flowerRepository.findById(flowerId)
                .orElseThrow(() -> new RuntimeException("꽃을 찾을 수 없습니다."));

        Basket basket = Basket.builder()
                .userId(userId)
                .flower(flower)
                .cnt(count)
                .build();

        return basketRepository.save(basket);
    }

    // 장바구니 삭제
    public void deleteBasket(Integer basketIdx) {
        basketRepository.deleteById(basketIdx);
    }
}

