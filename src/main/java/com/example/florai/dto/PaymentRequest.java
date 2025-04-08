package com.example.florai.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private Integer paymentID;
    private String id;
    private Integer orderIDX;
    private String flwID;
    private String flwName;
    private Integer cnt;
    private Integer price;
    private Long totalPrice;
}
