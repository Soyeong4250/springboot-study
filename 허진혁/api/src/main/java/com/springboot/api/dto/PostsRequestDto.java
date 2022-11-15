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
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Integer viewCount;

    @Builder
    public PostsRequestDto(Long id, String title, String writer, String content, LocalDateTime createdDate, LocalDateTime modifiedDate, Integer viewCount) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.viewCount = viewCount;
    }

    /* Dto -> Entity*/
    public Posts toEntity() {
        return Posts.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .viewCount(0)
                .build();
    }
}
