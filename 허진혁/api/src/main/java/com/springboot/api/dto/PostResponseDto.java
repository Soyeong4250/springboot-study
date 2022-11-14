package com.springboot.api.dto;

import com.springboot.api.domain.Period;
import com.springboot.api.domain.Posts;
import lombok.Getter;

/**
 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 */

@Getter
public class PostResponseDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private Period period;
    private Integer viewCount;

    /* Entity -> Dto*/
    public PostResponseDto(Posts posts) {
        this.id = posts.getId();
        this.title = posts.getTitle();
        this.writer = posts.getWriter();
        this.content = posts.getContent();
        this.period = posts.getPeriod();
        this.viewCount = posts.getViewCount();
    }
}
