package com.example.florai.controller;

import com.example.florai.dto.AnniversaryResponse;
import com.example.florai.entity.Anniversary;
import com.example.florai.repository.AnniversaryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<AnniversaryResponse>> getAllAnniversary() {
        List<Anniversary> anniversaries = anniversaryRepository.findAll();

        List<AnniversaryResponse> responses = anniversaries.stream()
                .map(anniversary -> {
                    AnniversaryResponse dto = new AnniversaryResponse();
                    dto.setAnniversaryId(anniversary.getAnniversaryId());
                    dto.setAnniversaryName(anniversary.getAnniversaryName());
                    dto.setAnniversaryDesc(anniversary.getAnniversaryDesc());
                    dto.setMonth(anniversary.getMonth());
                    dto.setDay(anniversary.getDay());
                    dto.setDDay(anniversary.getDDay());
                    return dto;
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }
}
