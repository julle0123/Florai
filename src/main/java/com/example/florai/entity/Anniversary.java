package com.example.florai.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "V_ANNIVERSARY_DDAY")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Anniversary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ANNI_IDX", nullable = false)
    private Integer anniversaryId;

    @Column(name = "ANNI_NAME", nullable = false, length = 255)
    private String anniversaryName;

    @Column(name = "ANNI_DESC", nullable = false)
    private String anniversaryDesc;

    @Column(name = "MONTH", nullable = false)
    private Integer month;

    @Column(name = "DAY", nullable = false)
    private Integer day;

    @Column(name = "D_DAY", nullable = false)
    private Integer dDay;
}
