package com.example.florai.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AIPickRequest {
    private Integer id;
    private String name;
    private Boolean spring;
    private Boolean summer;
    private Boolean fall;
    private Boolean winter;
    private String allergy;
    private String flwLang;
    private String flwSml;
    private String reason; // FastAPI에서 받은 추천 이유
}
