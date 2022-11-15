package com.springboot.api.dto;

import com.springboot.api.domain.Period;
import com.springboot.api.domain.Posts;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 */

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Integer viewCount;

    /* Entity -> Dto*/
    public PostsResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.writer = posts.getWriter();
        this.content = posts.getContent();
        this.createdDate = posts.getPeriod().getCreatedDate();
        this.modifiedDate = posts.getPeriod().getModifiedDate();
        this.viewCount = posts.getViewCount();
    }
}
