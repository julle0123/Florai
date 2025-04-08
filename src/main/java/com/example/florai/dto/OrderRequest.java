package com.example.florai.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Integer orderId;
    private String id;
    private String nick;
    private String flwName;
    private String payMethod;
    private String merchantId;
    private Integer totalPrice;
    private String addr;
    private String detailAddr;
    private String phone;
}
