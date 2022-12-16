package com.springboot.api.service;

import com.springboot.api.domain.User;
import com.springboot.api.domain.dto.UserCreateRequest;
import com.springboot.api.domain.dto.UserCreateResponse;
import com.springboot.api.jwt.JwtTokenProvider;
import com.springboot.api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.springboot.api.domain.Role.USER;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Value("${jwt.token.secret}")
    private String secretKey;
    private long expireTimeMs = 1000 * 60 * 60l;

    public UserCreateResponse join(UserCreateRequest request) {

        userRepository.findByUsername(request.getUsername()).ifPresent((user) -> {
            throw new RuntimeException("유저 이름이 존재");
        });

        User saveUser = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .nickname(request.getNickname())
                .email(request.getEmail())
                .role(USER)
                .build();

        userRepository.save(saveUser);
        return UserCreateResponse.of(saveUser);
    }

    public String login(String username, String password) {
        User savedUser = userRepository.findByUsername(username).orElseThrow(() -> {
            throw new RuntimeException("존재하지 않는 아이디");
        });

        if (!username.equals(savedUser.getUsername())) {
            throw new RuntimeException("아이디가 다릅니다.");
        }

        if (!passwordEncoder.matches(password, savedUser.getPassword())) {
            throw new RuntimeException("비밀번호가 다릅니다.");
        }

        return jwtTokenProvider.createToken(username, secretKey, expireTimeMs);
    }

}
