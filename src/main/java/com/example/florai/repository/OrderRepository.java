package com.example.florai.repository;

import com.example.florai.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByMerchantUid(String merchantUid); // merchantUid로 주문 조회
}
