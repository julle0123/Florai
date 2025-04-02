package com.example.florai.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 로그인 요청 시 프론트에서 보내는 JSON 데이터를 받는 DTO
 */
@Getter @Setter
public class LoginRequest {
    private String id;
    private String pw;
}