package com.springboot.api.service;

import com.springboot.api.domain.Posts;
import com.springboot.api.dto.PostResponseDto;
import com.springboot.api.dto.PostsRequestDto;
import com.springboot.api.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long register(PostsRequestDto dto) {
        Posts posts = dto.toEntity();
        postsRepository.save(posts);
        return posts.getId();
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostResponseDto> readById(Long id) {
        return postsRepository.findById(id).stream()
                .map(PostResponseDto::new)
                .collect(Collectors.toList());
    }
}
