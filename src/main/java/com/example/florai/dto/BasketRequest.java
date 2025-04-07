package com.example.florai.dto;

import com.example.florai.entity.Flower;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketRequest {
    private Integer BasketIdx;
    private String id;
    private Integer flowerIdx;
    private String flowerName;
    private Integer flowerPrice;
    private String flowerImg;
    private Integer cnt;
}
