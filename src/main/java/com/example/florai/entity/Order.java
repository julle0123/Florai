package com.example.florai.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity // JPA가 관리하는 엔티티 클래스
@Table(name = "T_ORDER")
@Data
@Getter // Lombok → getter 자동 생성
@Setter // Lombok → setter 자동 생성
@Builder
@NoArgsConstructor // Lombok → 기본 생성자 자동 생성
@AllArgsConstructor // Lombok → 모든 필드 포함 생성자 자동 생성
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_IDX", nullable = false)
    private Integer orderId;

    @Column(name = "ID", length = 50, nullable = false)
    private String id;

    @Column(name = "TOTAL_AMOUNT", nullable = false)
    private Integer totalAmount;

    @Column(name = "DISCOUNT_AMOUNT", nullable = false)
    private Integer discountAmount;

    @Column(name = "PAY_AMOUNT", nullable = false)
    private Integer payAmount;

    @Column(name = "PAY_METHOD", length = 10, nullable = false)
    private String payMethod;

    @Column(name = "PAID_AMOUNT", nullable = false)
    private Integer paidAmount;

    @Column(name = "ORDER_ST", length = 20, nullable = false)
    private String orderSt;

    @Column(name = "ORDERED_AT", nullable = false)
    private Timestamp orderedAt;

}
