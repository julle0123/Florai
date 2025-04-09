package com.example.florai.repository;

import com.example.florai.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    Order findByMerchantUid(String merchantUid); // merchantUid로 주문 조회
    List<Order> findAllByUser_Id(String userId);
}
