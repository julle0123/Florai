package com.example.florai.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.sql.Timestamp;


@Entity // JPA가 관리하는 엔티티 클래스
@Table(name = "T_MEMBER")
@Data
@Getter // Lombok → getter 자동 생성
@Setter // Lombok → setter 자동 생성
@Builder
@NoArgsConstructor // Lombok → 기본 생성자 자동 생성
@AllArgsConstructor // Lombok → 모든 필드 포함 생성자 자동 생성
@ToString
public class User {
    @Id
    @JsonProperty("ID")
    @Column(name = "ID", length = 50, nullable = false)
    private String id; // 회원 아이디 (PK)

    @JsonProperty("PW")
    @Column(name = "PW", length = 50, nullable = false)
    private String pw; // 비밀번호

    @JsonProperty("NICK")
    @Column(name = "NICK", length = 50, nullable = false)
    private String nick;

    @JsonProperty("PHONE")
    @Column(name = "PHONE", length = 20, nullable = false)
    private String phone;

    @JsonProperty("ADDR")
    @Column(name = "ADDR", length = 600, nullable = false)
    private String addr;

    @Column(name = "GRADE", nullable = false)
    private int grade;

    @Column(name = "SECESSION_YN", length = 1, nullable = false)
    private String secessionYn; // 'Y' 또는 'N'

    @Column(name = "JOINED_AT", nullable = false)
    private Timestamp joinedAt;

}
