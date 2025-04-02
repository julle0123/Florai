package com.example.florai.controller;

import com.example.florai.dto.LoginRequest;
import com.example.florai.entity.User;
import com.example.florai.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LoginController {

    private UserRepository userRepository;

    public void UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 로그인 API
     * @param request 프론트에서 전송한 로그인 요청(id, pw)
     * @return 로그인 결과 메시지
     */
    @PostMapping("/Login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByIdAndPw(request.getId(), request.getPw());

        if (user == null) {
            return ResponseEntity.status(401).body("❌ 로그인 실패: 아이디 또는 비밀번호가 틀렸습니다");
        }

        if ("Y".equalsIgnoreCase(user.getSecessionYn())) {
            return ResponseEntity.status(403).body("🚫 탈퇴한 사용자입니다");
        }

        return ResponseEntity.ok("✅ 로그인 성공! 반갑습니다, " + user.getNick() + "님");
    }
}

