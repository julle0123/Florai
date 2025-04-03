// com.example.florai.dto.RecommendRequest.java
package com.example.florai.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class RecommendRequest {
    private List<String> query;

}