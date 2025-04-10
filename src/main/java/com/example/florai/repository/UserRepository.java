package com.example.florai.repository;

import com.example.florai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    boolean existsById(String id); // 아이디 존재 여부 확인
    Optional<User> findById(String id);
}
