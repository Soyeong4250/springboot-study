package com.springboot.api.repository;

import com.springboot.api.domain.Posts;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @AfterEach
    void clear() {
        postsRepository.deleteAll();
    }

    @Test
    void PostsSaveAndGet() {
        Posts posts = Posts.builder()
                .title("title1")
                .content("content1")
                .writer("writer1")
                .build();
        postsRepository.save(posts);

        List<Posts> postsList = postsRepository.findAll();

        log.info("postsList={}", postsList.get(0));
    }

}