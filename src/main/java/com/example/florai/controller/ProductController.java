package com.example.florai.controller;

import com.example.florai.dto.ProductList;
import com.example.florai.entity.Flower;
import com.example.florai.repository.FlowerRepository;
import com.example.florai.dto.RecommendRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final FlowerRepository flowerRepository;

    @Autowired
    public ProductController(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    @GetMapping
    public ResponseEntity<List<ProductList>> getAllProducts() {
        List<Flower> flowers = flowerRepository.findAll();

        List<ProductList> productList = flowers.stream()
                .map(flower -> {
                    ProductList dto = new ProductList();
                    dto.setId(flower.getId());
                    dto.setName(flower.getName());
                    dto.setSituation(flower.getSituation());
                    dto.setPrice(flower.getPrice());
                    dto.setImage(flower.getFlwLmg());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(productList);
    }
}

