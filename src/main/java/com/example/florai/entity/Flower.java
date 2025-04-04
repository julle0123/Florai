package com.example.florai.entity;

import jakarta.persistence.*;
import lombok.*;
import jakarta.persistence.Id;

import java.sql.Timestamp;

@Entity // JPA가 관리하는 엔티티 클래스
@Table(name = "T_FLOWER")
@Data
@Getter // Lombok → getter 자동 생성
@Setter // Lombok → setter 자동 생성
@Builder
@NoArgsConstructor // Lombok → 기본 생성자 자동 생성
@AllArgsConstructor // Lombok → 모든 필드 포함 생성자 자동 생성
@ToString
public class Flower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FLW_IDX")
    private Integer id;

    @Column(name = "FLW_NAME", length = 255, nullable = false)
    private String name;

    @Column(name = "SPRING", nullable = false)
    private Boolean spring;

    @Column(name = "SUMMER", nullable = false)
    private Boolean summer;

    @Column(name = "FALL", nullable = false)
    private Boolean fall;

    @Column(name = "WINTER", nullable = false)
    private Boolean winter;

    @Column(name = "PRICE", nullable = false)
    private Integer price;

    @Column(name = "FLW_LANG", length = 255, nullable = false)
    private String flwLang;

    @Column(name = "FLW_IMG", length = 1000, nullable = false)
    private String flwLmg;

    @Column(name = "COLOR", length = 20, nullable = false)
    private String color;

    @Column(name = "STOCK", nullable = false)
    private Integer stock;

    @Column(name = "ALLERGY", nullable = false, length = 1)
    private String allergy;

    @Column(name = "CREATED_AT", updatable = false, nullable = false)
    private Timestamp createdAt;

    @Column(name = "FLW_SML", nullable = false, length = 255)
    private String flwSml;

    @Column(name = "SITUATION", nullable = false, length = 30)
    private String situation;

}
