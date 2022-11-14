package com.springboot.api.dto;


import com.springboot.api.domain.Period;
import com.springboot.api.domain.Posts;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PostsRequestDto {

    private Long id;
    private String title;
    private String writer;
    private String content;
    private Period period;
    private Integer viewCount;

    @Builder
    public PostsRequestDto(Long id, String title, String writer, String content, Period period, Integer viewCount) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.period = period;
        this.viewCount = viewCount;
    }

    public Posts toEntity() {
        return Posts.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .viewCount(viewCount)
                .build();
    }
}
