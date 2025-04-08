package com.example.florai.service;

import com.example.florai.dto.OrderRequest;
import com.example.florai.entity.Basket;
import com.example.florai.entity.Order;
import com.example.florai.entity.User;
import com.example.florai.repository.BasketRepository;
import com.example.florai.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final BasketRepository basketRepository;

    @Transactional
    public Order createOrder(OrderRequest orderRequest, User user) {
        Order order = Order.builder()
                .user(user)
                .name(orderRequest.getNick())
                .flwName(orderRequest.getFlwName())
                .payMethod(orderRequest.getPayMethod())
                .merchantUid(orderRequest.getMerchantId())
                .totalPrice(orderRequest.getTotalPrice())
                .addr(orderRequest.getAddr())
                .detailAddr(orderRequest.getDetailAddr())
                .phone(orderRequest.getPhone())
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .paymentStatus(false)
                .build();

        return orderRepository.save(order);
    }

    public Order getOrder(Integer orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found"));
    }

    public Order getOrderByMerchantUid(String merchantUid) {
        return orderRepository.findByMerchantUid(merchantUid);
    }

    public String getOrderCodeWithUUID() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String formattedDay = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        return formattedDay + "-" + uuid;
    }

    @Transactional
    public void updatePaymentStatus(Order order, boolean status) {
        order.setPaymentStatus(status);
        orderRepository.save(order);

        // ✅ 장바구니 상품 삭제
        String userId = order.getUser().getId();
        List<Basket> baskets = basketRepository.findById(userId);
        basketRepository.deleteAll(baskets);
    }
}
