package com.example.florai.repository;

import com.example.florai.entity.Anniversary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnniversaryRepository extends JpaRepository<Anniversary, Integer> {
}
