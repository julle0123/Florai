package com.example.florai.repository;

import com.example.florai.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

    // 로그인용 쿼리: ID + PW가 모두 일치하는 사용자 조회
    User findByIdAndPw(String id, String pw);
}
