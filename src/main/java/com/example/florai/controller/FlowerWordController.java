package com.example.florai.controller;

import com.example.florai.dto.FlowerWordRequest;
import com.example.florai.entity.Flower_word;
import com.example.florai.repository.FlowerWordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/FlowerWord")
public class FlowerWordController {

    private final FlowerWordRepository flowerWordRepository;

    @Autowired
    public FlowerWordController(FlowerWordRepository flowerWordRepository) {
        this.flowerWordRepository = flowerWordRepository;
    }

    @GetMapping
    public ResponseEntity<List<FlowerWordRequest>> getAllProducts() {
        List<Flower_word> flowerwords = flowerWordRepository.findAll();

        List<FlowerWordRequest> flowerWordRequests = flowerwords.stream()
                .map(flower -> {
                    FlowerWordRequest dto = new FlowerWordRequest();
                    dto.setId(flower.getId());
                    dto.setName(flower.getName());
                    dto.setFlwLang(flower.getFlwLang());
                    dto.setImg(flower.getImg());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(flowerWordRequests);
    }
}
