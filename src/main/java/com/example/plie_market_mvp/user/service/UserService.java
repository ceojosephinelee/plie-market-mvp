package com.example.plie_market_mvp.user.service;

import com.example.plie_market_mvp.user.dto.LoginRequest;
import com.example.plie_market_mvp.user.dto.SignupRequest;
import com.example.plie_market_mvp.user.dto.UserResponse;
import com.example.plie_market_mvp.user.entity.User;
import com.example.plie_market_mvp.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public void signup(SignupRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }
        if (userRepository.existsByNickname(request.getNickname())) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        String hashedPassword = passwordEncoder.encode(request.getPassword());
        User user = new User(request.getNickname(), request.getEmail(), hashedPassword);
        userRepository.save(user);
    }

    public UserResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 계정입니다."));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        // JWT 발급은 추후 추가
        return new UserResponse(user.getUserId(), user.getNickname(), user.getEmail());
    }
}
