package com.example.florai.controller;

import com.example.florai.dto.OrderRequest;
import com.example.florai.entity.Order;
import com.example.florai.entity.User;
import com.example.florai.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    // 주문 생성
    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest orderRequest) {
        // 일단 userId나 user는 요청에서 받아야 하니까 가정
        // (User 객체를 가져오는 부분은 실제로는 SecurityContextHolder나 로그인 정보를 통해 가져와야 함)

        User user = new User();
        user.setId(orderRequest.getId());
        user.setNick(orderRequest.getNick());

        Order createdOrder = orderService.createOrder(orderRequest, user);

        return ResponseEntity.ok().body(
                "주문이 생성되었습니다. 주문번호: " + createdOrder.getOrderId()
        );
    }

    // 특정 주문 조회 (프론트에서 결제 전 정보 조회할 때 사용 가능)
    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Integer orderId) {
        Order order = orderService.getOrder(orderId);
        return ResponseEntity.ok(order);
    }

    // 결제 성공 후 주문 상태 업데이트
    @PostMapping("/{orderId}/payment-success")
    public ResponseEntity<?> paymentSuccess(@PathVariable Integer orderId) {
        Order order = orderService.getOrder(orderId);
        orderService.updatePaymentStatus(order, true);

        return ResponseEntity.ok("결제 완료 및 주문 상태 업데이트 성공");
    }
}
