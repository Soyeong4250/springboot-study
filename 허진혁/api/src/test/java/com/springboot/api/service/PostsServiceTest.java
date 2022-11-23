package com.springboot.api.service;

import com.springboot.api.dto.PostRequestDto;
import com.springboot.api.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class PostsServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    PostRepository postRepository;

    @AfterEach
    void clear() {
        postRepository.deleteAll();
    }

    @Test
    void 게시글_생성() {
        PostRequestDto posts = PostRequestDto.builder()
                .title("Test title")
                .writer("Test writer")
                .content("Test content")
                .viewCount(10)
                .build();

        postService.write(posts);
        log.info("posts={}", posts);
    }
}