package com.springboot.api.controller;

import com.springboot.api.domain.dto.CommentRequestDto;
import com.springboot.api.domain.dto.CommentResponseDto;
import com.springboot.api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommentApiController {

    private final CommentService commentService;

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<CommentResponseDto> createComment(@PathVariable Long id,
                                                            @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto response = commentService.createComment(commentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentResponseDto>> findAll(@PathVariable Long postId) {
        List<CommentResponseDto> response = commentService.findAll(postId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentResponseDto> update(@PathVariable Long postId,
                                                     @PathVariable Long commentId,
                                                     @RequestBody CommentRequestDto commentRequestDto) {
        CommentResponseDto response = commentService.update(commentRequestDto);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity delete(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.delete(commentId);
        return ResponseEntity.status(HttpStatus.OK).body(commentId);
    }
}
