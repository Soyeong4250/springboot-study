package com.springboot.api.controller;

import com.springboot.api.dto.PostsRequestDto;
import com.springboot.api.service.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class PostsApiController {

    private final PostsService postsService;

    /**
     * 글작성
     */
    @PostMapping("/posts")
    public ResponseEntity<Long> register(@RequestBody PostsRequestDto dto) {
        Long registerId = postsService.write(dto);
        return new ResponseEntity<>(registerId, HttpStatus.OK);
    }
}
