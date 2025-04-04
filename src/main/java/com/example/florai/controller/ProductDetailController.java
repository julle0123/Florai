package com.example.florai.controller;

import com.example.florai.dto.ProductDetail;
import com.example.florai.entity.Flower;
import com.example.florai.repository.FlowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ProductsDetail")
public class ProductDetailController {

    private final FlowerRepository flowerRepository;

    @Autowired
    public ProductDetailController(FlowerRepository flowerRepository) {
        this.flowerRepository = flowerRepository;
    }

    // 제품 리스트 보내는 엔드포인트
    @GetMapping
    public ResponseEntity<List<ProductDetail>> getAllProducts() {
        List<Flower> flowers = flowerRepository.findAll();

        List<ProductDetail> ProductDetail = flowers.stream()
                .map(flower -> {
                    ProductDetail dto = new ProductDetail();
                    dto.setId(flower.getId());
                    dto.setName(flower.getName());
                    dto.setSituation(flower.getSituation());
                    dto.setPrice(flower.getPrice());
                    dto.setImage(flower.getFlwLmg());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(ProductDetail);
    }
}
