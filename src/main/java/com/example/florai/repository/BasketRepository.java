package com.example.florai.repository;


import com.example.florai.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Integer> {
    List<Basket> findById(String id);  // 사용자별 장바구니 조회
}