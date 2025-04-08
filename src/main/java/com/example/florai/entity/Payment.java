package com.example.florai.entity;

import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@Entity
@Table(name = "T_PAYMENT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PAYMENT_ID")
    private Integer paymentID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID", nullable = false)
    private User user; // 외래키 - 사용자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_IDX", nullable = false)
    private Order order; // 외래키 - 주문

    @Column(name = "FLW_ID", nullable = false)
    private Integer flwId;

    @Column(name = "FLW_NAME", nullable = false, length = 255)
    private String flwName;

    @Column(name = "CNT", nullable = false)
    private Integer cnt;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @Column(name = "TOTAL_PRICE", nullable = false)
    private Long totalPrice;

    @Column(name = "PAID_AT", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp paidAt;

    @Column(name = "PAY_ST", nullable = false, columnDefinition = "TINYINT(1) DEFAULT 1")
    private Boolean paySt;
}
