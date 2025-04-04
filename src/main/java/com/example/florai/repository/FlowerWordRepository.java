package com.example.florai.repository;


import com.example.florai.entity.Flower_word;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowerWordRepository extends JpaRepository<Flower_word, Integer> {
    List<Flower_word> findByIdIn(List<Integer> ids);
}
