package com.example.florai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // JPA가 관리하는 엔티티 클래스
@Table(name = "T_ANNIVERSARY")
@Data
@Getter // Lombok → getter 자동 생성
@Setter // Lombok → setter 자동 생성
@Builder
@NoArgsConstructor // Lombok → 기본 생성자 자동 생성
@AllArgsConstructor // Lombok → 모든 필드 포함 생성자 자동 생성
@ToString
public class Anniversary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNI_IDX", nullable = false)
    private Integer anniversaryid;

    @Column(name = "ANNI_NAME", nullable = false, length = 255)
    private String anniversaryname;

    @Column(name = "ANNI_DESC", nullable = false)
    private String anniversarydesc;

    @Column(name = "MONTH", nullable = false)
    private Integer month;

    @Column(name = "DAY", nullable = false)
    private Integer day;
}
