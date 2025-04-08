package com.example.florai.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "T_ORDER")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_IDX")
    private Integer orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", nullable = false)
    private User user; // 회원 ID

    @Column(name = "NICK", nullable = false, length = 50)
    private String name;

    @Column(name = "FLW_NAME", nullable = false, length = 255)
    private String flwName;

    @Column(name = "PAY_METHOD", nullable = false, length = 10)
    private String payMethod;

    @Column(name = "MERCHANT_UID", nullable = false, length = 100)
    private String merchantUid;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Integer totalPrice;

    @Column(name = "ADDR", nullable = false, length = 600)
    private String addr;

    @Column(name="DETAIL_ADDR", nullable = false, length = 255)
    private String detailAddr;

    @Column(name = "PHONE", nullable = false, length = 20)
    private String phone;

    @Column(name = "CREATED_AT", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    @Column(name = "PAYMENT_STATUS", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 0")
    private Boolean paymentStatus;
}
