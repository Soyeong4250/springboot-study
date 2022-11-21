package com.springboot.api.dto;


import com.springboot.api.domain.Post;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter @Setter
public class PostRequestDto {

    private String title;
    private String writer;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Integer viewCount;

    @Builder
    public PostRequestDto(String title, String writer, String content, LocalDateTime createdDate, LocalDateTime modifiedDate, Integer viewCount) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.viewCount = viewCount;
    }

    /* Dto -> Entity*/
    public Post toEntity() {
        return Post.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .viewCount(0)
                .build();
    }
}
