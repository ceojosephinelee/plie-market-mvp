package com.example.plie_market_mvp.user.controller;

import com.example.plie_market_mvp.common.dto.AuthResponse;
import com.example.plie_market_mvp.common.security.JwtTokenProvider;
import com.example.plie_market_mvp.user.dto.LoginRequest;
import com.example.plie_market_mvp.user.dto.SignupRequest;
import com.example.plie_market_mvp.user.dto.UserResponse;
import com.example.plie_market_mvp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignupRequest request) {
        userService.signup(request);
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        UserResponse response = userService.login(request);
        String token= jwtTokenProvider.createToken(response.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}


