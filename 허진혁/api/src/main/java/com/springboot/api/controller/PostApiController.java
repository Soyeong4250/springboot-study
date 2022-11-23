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

    /** 게시물 단건 조회*/
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> get(@PathVariable Long id) {
        log.info("게시물 단건 조회. id={}", id);
        PostResponseDto findPosts = postService.getById(id);
        return ResponseEntity.ok().body(findPosts);
    }

    /**게시물 페이징 조회*/
    @GetMapping("")
    public ResponseEntity<Page<PostResponseDto>> getAll(@PageableDefault Pageable pageable) {
        log.info("전체 게시글 조회. pageNumber={}, pagzSize={}", pageable.getPageNumber(), pageable.getPageSize());
        Page<PostResponseDto> page = postService.getAll(pageable);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping("/new")
    public ResponseEntity<PostResponseDto> write(@RequestBody PostRequestDto postRequestDto) {
        log.info("글쓰기 요청. writer={}, title={}", postRequestDto.getWriter(), postRequestDto.getTitle());
        PostResponseDto response = postService.write(postRequestDto);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/update")
    public ResponseEntity<PostResponseDto> update(@RequestBody PostRequestDto postRequestDto) {
        log.info("글 수정. writer={}, title={}", postRequestDto.getWriter(), postRequestDto.getTitle());
        PostResponseDto response = postService.update(postRequestDto);
        return ResponseEntity.ok().body(response);
    }
}
