package com.example.florai.service;

import com.example.florai.dto.OrderDetailResponse;
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
import java.util.stream.Collectors;

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
                .paymentStatus(0)
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
    public void updatePaymentStatus(Order order, Integer status) {
        order.setPaymentStatus(status);
        orderRepository.save(order);

        // 장바구니 상품 삭제
        String userId = order.getUser().getId();
        List<Basket> baskets = basketRepository.findById(userId);
        basketRepository.deleteAll(baskets);
    }

    // 사용자별 주문 목록 조회 (수정 완료)
    @Transactional(readOnly = true)
    public List<OrderDetailResponse> getOrdersByUserId(String userId) {
        List<Order> orders = orderRepository.findAllByUser_Id(userId);

        return orders.stream()
                .map(order -> new OrderDetailResponse(
                        order.getOrderId(),
                        order.getUser().getId(),
                        order.getUser().getNick(),
                        order.getFlwName(),
                        order.getPayMethod(),
                        order.getMerchantUid(),
                        order.getTotalPrice(),
                        order.getAddr(),
                        order.getDetailAddr(),
                        order.getPhone(),
                        order.getCreatedAt(),
                        order.getPaymentStatus()
                ))
                .collect(Collectors.toList());
    }
}
