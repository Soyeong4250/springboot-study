package com.springboot.api.service;

import com.springboot.api.domain.Comment;
import com.springboot.api.domain.Post;
import com.springboot.api.domain.dto.CommentRequestDto;
import com.springboot.api.domain.dto.CommentResponseDto;
import com.springboot.api.repository.CommentRepository;
import com.springboot.api.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentResponseDto createComment(CommentRequestDto commentRequestDto) {
        Comment comment = commentRequestDto.toEntity();
        commentRepository.save(comment);
        CommentResponseDto response = CommentResponseDto.from(comment);
        return response;
    }

    @Transactional(readOnly = true)
    public List<CommentResponseDto> findAll(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> {
            throw new IllegalStateException(postId + "의 게시글이 존재하지 않습니다.");
        });

        List<Comment> comments = commentRepository.findByPostOrderById(post);
        List<CommentResponseDto> response = comments.stream().map(CommentResponseDto::from)
                .collect(Collectors.toList());
        return response;
    }

    public CommentResponseDto update(CommentRequestDto commentRequestDto) {
        Comment comment = commentRepository.findById(commentRequestDto.getId()).orElseThrow(() -> {
            throw new IllegalStateException(commentRequestDto.getId() + "의 댓글이 존재하지 않습니다.");
        });

        Comment updateComment = commentRepository.save(comment);
        CommentResponseDto response = CommentResponseDto.from(updateComment);
        return response;
    }

    public void delete(Long commentId) {
        commentRepository.findById(commentId).orElseThrow(() -> {
            throw new IllegalStateException(commentId + "의 댓글이 존재하지 않습니다.");
        });

        commentRepository.deleteById(commentId);
    }

}
