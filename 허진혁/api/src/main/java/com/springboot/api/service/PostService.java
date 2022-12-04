package com.springboot.api.service;

import com.springboot.api.domain.Post;
import com.springboot.api.dto.PostResponseDto;
import com.springboot.api.dto.PostRequestDto;
import com.springboot.api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public PostResponseDto createPost(PostRequestDto dto) {
        log.info("PostsService write()실행. writer={}, title={}", dto.getWriter(), dto.getTitle());
        Post post = dto.toEntity();
        postRepository.save(post);
        PostResponseDto response = PostResponseDto.from(post);
        return response;
    }

    @Transactional(readOnly = true)
    public Page<PostResponseDto> findAllByPage(Pageable pageable) {
        Page<Post> pages = postRepository.findAll(pageable);
        return pages.map(PostResponseDto::from);
    }

    @Transactional(readOnly = true)
    public PostResponseDto findById(Long id) {
        Optional<Post> findPosts = Optional.ofNullable(postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글은 존재하지 않습니다. id: " + id)));
        Post post = findPosts.get();
        PostResponseDto response = PostResponseDto.from(post);
        return response;
    }

    /** 정렬된 페이지*/
    @Transactional(readOnly = true)
    public Page<Post> pageList(Pageable pageable) {
        Page<Post> pages = postRepository.findAll(pageable);
        return pages;
    }

    @Transactional(readOnly = true)
    public Page<Post> search(Pageable pageable, String keyword) {
        Page<Post> pages = postRepository.findByTitleContaining(pageable, keyword);
        return pages;
    }

    @Transactional
    public PostResponseDto update(PostRequestDto dto) {
        log.info("PostsService update()실행. writer={}, title={}", dto.getWriter(), dto.getTitle());
        Post post = dto.toEntity();
        postRepository.save(post);
        PostResponseDto response = PostResponseDto.from(post);
        return response;
    }

    @Transactional
    public int updateViewCount(Long postId) {
        return postRepository.updateViewCount(postId);
    }

    @Transactional
    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
