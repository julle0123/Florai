package com.example.florai.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AnniversaryResponse {
    private Integer anniversaryId;
    private String anniversaryName;
    private String anniversaryDesc;
    private Integer month;
    private Integer day;
    private Integer dDay;
}
