package com.springboot.api.controller;

import com.springboot.api.dto.PostRequestDto;
import com.springboot.api.dto.PostResponseDto;
import com.springboot.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class PostApiController {

    private final PostService postService;

    @PostMapping("/posts/new")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postRequestDto) {
        log.info("글쓰기 요청. writer={}, title={}", postRequestDto.getWriter(), postRequestDto.getTitle());
        PostResponseDto response = postService.createPost(postRequestDto);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostResponseDto> findById(@PathVariable Long id) {
        log.info("게시물 단건 조회. id={}", id);
        PostResponseDto findPosts = postService.findById(id);
        return ResponseEntity.ok().body(findPosts);
    }

    @GetMapping("/posts")
    public ResponseEntity<Page<PostResponseDto>> findAllByPage(@PageableDefault Pageable pageable) {
        log.info("전체 게시글 조회. pageNumber={}, pagzSize={}", pageable.getPageNumber(), pageable.getPageSize());
        Page<PostResponseDto> page = postService.findAllByPage(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PatchMapping("/posts/{id}/edit")
    public ResponseEntity<PostResponseDto> update(@PathVariable Long id, @RequestBody PostRequestDto postRequestDto) {
        log.info("글 수정. writer={}, title={}", postRequestDto.getWriter(), postRequestDto.getTitle());
        PostResponseDto response = postService.update(postRequestDto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity deleteById(@PathVariable Long id) {
        postService.deleteById(id);
        return ResponseEntity.ok().body(id);
    }
}
