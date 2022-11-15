package com.springboot.api.service;

import com.springboot.api.dto.PostsRequestDto;
import com.springboot.api.repository.PostsRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
class PostsServiceTest {

    @Autowired
    private PostsService postsService;

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    void clear() {
        postsRepository.deleteAll();
    }

    @Test
    void 게시글_생성() {
        PostsRequestDto posts = PostsRequestDto.builder()
                .title("Test title")
                .writer("Test writer")
                .content("Test content")
                .viewCount(10)
                .build();

        postsService.write(posts);
        log.info("posts={}", posts);
    }
}