package com.springboot.api.service;

import com.springboot.api.repository.PostRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
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

    /*@Test
    void 게시글_생성() {
        PostRequestDto posts = new PostRequestDto("Test title", "Test writer", "Test content", "2022.12.04", 0);


        postService.createPost(posts);
        log.info("posts={}", posts);
    }*/
}