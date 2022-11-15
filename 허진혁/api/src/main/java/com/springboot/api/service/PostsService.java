package com.springboot.api.service;

import com.springboot.api.domain.Posts;
import com.springboot.api.dto.PostsResponseDto;
import com.springboot.api.dto.PostsRequestDto;
import com.springboot.api.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long write(PostsRequestDto dto) {
        Posts posts = dto.toEntity();
        log.info("PostsService save()실행");
        postsRepository.save(posts);
        return posts.getId();
    }

    @Transactional(readOnly = true)
    public List<PostsResponseDto> findAllDesc() {
        return postsRepository.findAllByOrderByIdDesc().stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostsResponseDto> read(Long id) {
        return postsRepository.findById(id).stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }
}
