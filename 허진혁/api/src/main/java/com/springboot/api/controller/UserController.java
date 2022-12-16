package com.springboot.api.controller;

import com.springboot.api.domain.dto.UserCreateRequest;
import com.springboot.api.domain.dto.UserLoginRequest;
import com.springboot.api.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @GetMapping("/auth/join")
    public String joinForm() {
        return "/user/join";
    }

    @PostMapping("/auth/join")
    public String join(UserCreateRequest request, Model model) {
        log.info("request.getUsername={}", request.getUsername());
        log.info("request.getNickname={}", request.getNickname());
        model.addAttribute("UserCreateRequest", request);
        userService.join(request);
        return "redirect:/auth/login";
    }

    @GetMapping("/auth/login")
    public String loginForm() {
        return "/user/login";
    }

    @PostMapping("/auth/login")
    public String login(UserLoginRequest request) {
        userService.login(request.getUsername(), request.getPassword());
        return "redirect:/posts/list";
    }

    @GetMapping("/auth/modify")
    public String updateForm(@PathVariable Long id) {
        return "user/edit";
    }

    @PostMapping("/auth/{id}/update")
    public String update(@PathVariable Long id) {
        return "redirect:/auth/login";
    }

}
