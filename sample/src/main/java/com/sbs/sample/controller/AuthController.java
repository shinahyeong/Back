package com.sbs.sample.controller;

import com.sbs.sample.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000") //동일 출처 에러 방지를 위함
@RestController // Rest API 통신을 위한 것 -> 프론트엔드의 JSON데이터를 역직렬화 지원
@RequestMapping("/auth")    //url 경로 -> http://localhost:8111/auth와 동일
@RequiredArgsConstructor    // 생성자를 자동으로 만들어줌

public class AuthController {
    private final AuthService authService;  //의존성 주입

    // 회원 가입 여부 확인 ->http://localhost:8111/auth/exists/{email} 형식으로 써야함
    @GetMapping("/exists/{email}")
    public ResponseEntity<Boolean> memberExists(@PathVariable String email){
        log.info("email: {}", email);
        boolean isTrue = authService.isMember(email);
        return ResponseEntity.ok(!isTrue);
    }
}
