package com.example.florai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity // JPA가 관리하는 엔티티 클래스
@Table(name = "T_FLOWER_WORD")
@Data
@Getter // Lombok → getter 자동 생성
@Setter // Lombok → setter 자동 생성
@Builder
@NoArgsConstructor // Lombok → 기본 생성자 자동 생성
@AllArgsConstructor // Lombok → 모든 필드 포함 생성자 자동 생성
@ToString
public class Flower_word {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FW_IDX")
    private Integer fw_idx;

    @Column(name = "FW_NAME", nullable = false, length = 255)
    private String name;

    @Column(name = "FW_LANG", length = 255)
    private String flw_lang;
}
