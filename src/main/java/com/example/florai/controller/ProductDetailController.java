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

    // ✅ 단일 제품 정보를 id로 조회
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDetail> getProductById(@PathVariable Long id) {
        // DB에서 id에 해당하는 꽃을 찾음
        Flower flower = flowerRepository.findById(Math.toIntExact(id))
                .orElseThrow(() -> new RuntimeException("해당 ID의 상품을 찾을 수 없습니다."));

        // 엔티티 → DTO로 변환
        ProductDetail dto = new ProductDetail();
        dto.setId(flower.getId());
        dto.setName(flower.getName());
        dto.setSpring(flower.getSpring());
        dto.setSummer(flower.getSummer());
        dto.setFall(flower.getFall());
        dto.setWinter(flower.getWinter());
        dto.setAllergy(flower.getAllergy());
        dto.setColor(flower.getColor());
        dto.setFlwLang(flower.getFlwLang());
        dto.setFlwSml(flower.getFlwSml());
        dto.setSituation(flower.getSituation());
        dto.setPrice(flower.getPrice());
        dto.setImage(flower.getFlwLmg());

        return ResponseEntity.ok(dto);
    }
}

