package com.example.florai.repository;

import com.example.florai.entity.Flower;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlowerRepository extends JpaRepository<Flower, Integer> {
    List<Flower> findByIdIn(List<Integer> ids);
}
