package com.example.florai.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetail {
    private int id;
    private String name;
    private Boolean spring;
    private Boolean summer;
    private Boolean fall;
    private Boolean winter;
    private String allergy;
    private String color;
    private String flwLang;
    private String flwSml;
    private String situation;
    private int price;
    private String image;

}
