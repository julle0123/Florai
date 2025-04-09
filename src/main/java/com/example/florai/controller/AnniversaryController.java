package com.example.florai.controller;

import com.example.florai.dto.AnniversayRequset;
import com.example.florai.entity.Anniversary;
import com.example.florai.repository.AnniversaryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/anniversary")
public class AnniversaryController {

    private final AnniversaryRepository anniversaryRepository;

    public AnniversaryController(AnniversaryRepository anniversaryRepository) {
        this.anniversaryRepository = anniversaryRepository;
    }

    @GetMapping
    public ResponseEntity<List<AnniversayRequset>> GetAllAnniversary() {
        List<Anniversary> anniversaries = anniversaryRepository.findAll();

        List<AnniversayRequset> anniversayRequsets = anniversaries.stream()
                .map(anniversary ->{
                    AnniversayRequset dto = new AnniversayRequset();
                    dto.setAnniversaryId(anniversary.getAnniversaryId());
                    dto.setAnniversaryName(anniversary.getAnniversaryName());
                    dto.setAnniversaryDesc(anniversary.getAnniversaryDesc());
                    dto.setMonth(anniversary.getMonth());
                    dto.setDay(anniversary.getDay());
                    dto.setDDay(anniversary.getDDay());
                    return dto;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(anniversayRequsets);
    }
}
