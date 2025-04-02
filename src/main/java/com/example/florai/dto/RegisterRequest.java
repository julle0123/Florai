package com.example.florai.dto;

import lombok.Getter;
import lombok.Setter;
@Getter @Setter
public class RegisterRequest {
    private String id;
    private String pw;
    private String nick;
    private String addr;
    private String phone;
}
