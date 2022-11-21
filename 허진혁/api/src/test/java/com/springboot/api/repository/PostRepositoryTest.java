package com.springboot.api.repository;

import com.springboot.api.domain.Post;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
class PostRepositoryTest {

    @Autowired
    private PostRepository postRepository;

    @AfterEach
    void clear() {
        postRepository.deleteAll();
    }

    @Test
    void PostsSaveAndGet() {
        Post posts = Post.builder()
                .title("title1")
                .content("content1")
                .writer("writer1")
                .build();
        postRepository.save(posts);

        List<Post> postsList = postRepository.findAll();

        log.info("postsList={}", postsList.get(0));
    }

}