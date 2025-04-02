package com.example.florai.controller;

import com.example.florai.JWT.JwtUtil;
import com.example.florai.dto.LoginRequest;
import com.example.florai.entity.User;
import com.example.florai.repository.UserRepository;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/Login")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    public AuthController(JwtUtil jwtUtil, UserRepository userRepository) {
        this.jwtUtil = jwtUtil;
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. DB에서 userId로 사용자 조회
        Optional<User> optionalUser = userRepository.findById(request.getId());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("존재하지 않는 사용자입니다.");
        }

        User user = optionalUser.get();

        // 2. 비밀번호 일치 여부 확인
        if (!user.getPw().equals(request.getPw())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("비밀번호가 일치하지 않습니다.");
        }

        // 3. JWT 발급
        String token = jwtUtil.generateToken(user.getId());
        System.out.println(token);

        // 4. 클라이언트에 사용자 정보도 함께 전달
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("userId", user.getId());
        response.put("name", user.getNick());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/secure")
    public ResponseEntity<?> secureTest(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "");

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("유효하지 않은 토큰");
        }

        String userId = jwtUtil.getUserIdFromToken(token);
        return ResponseEntity.ok("안녕하세요 " + userId + "님!");
    }
}

