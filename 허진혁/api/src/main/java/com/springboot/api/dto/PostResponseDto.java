package com.springboot.api.dto;

import com.springboot.api.domain.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private Integer viewCount;

    @Builder
    public PostResponseDto(Long id, String title, String writer, String content, LocalDate createdDate, LocalDate modifiedDate, Integer viewCount) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.viewCount = viewCount;
    }
    /* Entity -> response*/
    public static PostResponseDto from(Post post) {
        return new PostResponseDto(
                post.getId(),
                post.getTitle(),
                post.getWriter(),
                post.getContent(),
                post.getCreatedDate(),
                post.getModifiedDate(),
                post.getViewCount());
    }
}
