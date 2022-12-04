package com.springboot.api.domain.dto;

import com.springboot.api.domain.Comment;
import com.springboot.api.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {

    private Long id;
    private String comment;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private Post post;

    public Comment toEntity() {
        return Comment.builder()
                .id(id)
                .comment(comment)
                .post(post)
                .build();
    }
}
