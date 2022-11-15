package com.springboot.api.controller;

import com.springboot.api.repository.PostsRepository;
import com.springboot.api.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class PostsController {

    private final PostsService postsService;
    private final PostsRepository postsRepository;

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/new")
    public String register() {
        return "form/new";
    }


}
