package com.example.florai.repository;

import com.example.florai.entity.Anniversary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnniversaryRepository extends JpaRepository<Anniversary, Integer> {
    List<Anniversary> findByAnniversaryId(Integer anniversaryId);
}
