// OrderDetailResponse.java
package com.example.florai.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class OrderDetailResponse {
    private Integer orderId;
    private String userId;
    private String nick;  // 닉네임
    private String flwName;  // 꽃 이름
    private String payMethod;  // 결제 수단
    private String merchantId;
    private Integer totalPrice;  // 총 금액
    private String addr;  // 주소
    private String detailAddr;  // 상세 주소
    private String phone;  // 전화번호
    private Timestamp createdAt;  // 생성일
    private Integer paymentStatus;  // 결제 상태
}
