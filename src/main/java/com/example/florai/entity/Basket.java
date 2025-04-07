package com.example.florai.entity;


import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity // JPA가 관리하는 엔티티 클래스
@Table(name = "T_BASKET")
@Data
@Getter // Lombok → getter 자동 생성
@Setter // Lombok → setter 자동 생성
@Builder
@NoArgsConstructor // Lombok → 기본 생성자 자동 생성
@AllArgsConstructor // Lombok → 모든 필드 포함 생성자 자동 생성
@ToString
public class Basket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BASKET_IDX", nullable = false)
    private Integer basketIdx;

    @Column(name = "ID", length = 50, nullable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FLW_IDX") // T_BASKET의 FLW_IDX -> T_FLOWER의 FLW_IDX
    private Flower flower;

    @Column(name = "CNT", nullable = false)
    private Integer cnt;

    @Column(name = "CREATED_AT", nullable = false)
    private Timestamp createdAt;

    @Column(name = "BASKET_ST", length = 20, nullable = false)
    private String basketSt;

}
