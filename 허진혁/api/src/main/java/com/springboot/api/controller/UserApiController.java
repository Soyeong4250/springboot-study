package com.springboot.api.controller;

import com.springboot.api.domain.dto.UserCreateRequest;
import com.springboot.api.domain.dto.UserCreateResponse;
import com.springboot.api.domain.dto.UserLoginRequest;
import com.springboot.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class UserApiController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<UserCreateResponse> join(@RequestBody UserCreateRequest request) {
        UserCreateResponse response = userService.join(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginRequest request) {
        log.info("request.getUsername()={}", request.getUsername());
        log.info("request.getPassword()={}", request.getPassword());
        userService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.status(HttpStatus.OK).body("");
    }

}
