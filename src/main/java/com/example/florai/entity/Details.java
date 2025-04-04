package com.example.florai.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity // JPA가 관리하는 엔티티 클래스
@Table(name = "T_DETAILS")
@Data
@Getter // Lombok → getter 자동 생성
@Setter // Lombok → setter 자동 생성
@Builder
@NoArgsConstructor // Lombok → 기본 생성자 자동 생성
@AllArgsConstructor // Lombok → 모든 필드 포함 생성자 자동 생성
@ToString
public class Details {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DETAIL_IDX", nullable = false)
    private Integer detailidx;

    @Column(name = "ORDER_IDX", nullable = false)
    private Integer orderidx;

    @Column(name = "FLW_IDX", nullable = false)
    private Integer flwidx;

    @Column(name = "CNT", nullable = false)
    private Integer cnt;
}
