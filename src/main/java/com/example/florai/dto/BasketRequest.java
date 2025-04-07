package com.example.florai.dto;

import com.example.florai.entity.Flower;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BasketRequest {
    private Integer BasketId;
    private String userId;
    private Flower flower;
    private Integer cnt;

}
