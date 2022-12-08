package com.springboot.api.domain.dto;

import com.springboot.api.domain.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentResponseDto {

    private Long id;
    private String comment;
    private String createdDate;
    private String modifiedDate;
    private Long userId;
    private Long postId;

    @Builder
    public CommentResponseDto(Long id, String comment, String createdDate, String modifiedDate, Long userId, Long postId) {
        this.id = id;
        this.comment = comment;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.userId = userId;
        this.postId = postId;
    }

    public static CommentResponseDto from(Comment comment) {
        return new CommentResponseDto(
                comment.getId(),
                comment.getComment(),
                comment.getCreatedDate(),
                comment.getModifiedDate(),
                comment.getUser().getId(),
                comment.getPost().getId()
        );
    }
}
