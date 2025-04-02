package com.example.florai.controller;

import com.example.florai.dto.RegisterRequest;
import com.example.florai.entity.User;
import com.example.florai.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestController // ✅ 핵심: JSON 응답을 기본으로 하는 컨트롤러
@RequiredArgsConstructor
@RequestMapping("/Signup")
public class RegisterController {

    private final UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request) {
        System.out.println("✅ RegisterRequest: " + request); // null이면 문제 있음

        // ID 중복 체크
        if (userRepository.existsById(request.getId())) {
            return ResponseEntity.status(409).body("❌ 이미 사용 중인 ID입니다");
        }

        // 사용자 정보 생성 및 저장
        User user = User.builder()
                .id(request.getId())
                .pw(request.getPw())
                .nick(request.getNick())
                .phone(request.getPhone())
                .addr(request.getAddr())
                .grade(1)
                .secessionYn("N")
                .joinedAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        userRepository.save(user);

        return ResponseEntity.ok("✅ 회원가입 성공!");
    }
}
