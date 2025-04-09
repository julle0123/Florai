// OrderDetailController.java
package com.example.florai.controller;

import com.example.florai.dto.OrderDetailResponse;
import com.example.florai.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order/detail")
public class OrderDetailController {

    private final OrderService orderService;

    @GetMapping("/{userId}")
    public List<OrderDetailResponse> getOrdersByUserId(@PathVariable String userId) {
        return orderService.getOrdersByUserId(userId);
    }
}
