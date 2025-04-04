package com.example.florai.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FlowerWordRequest {
    private Integer id;
    private String name;
    private String flwLang;
    private String img;
}
