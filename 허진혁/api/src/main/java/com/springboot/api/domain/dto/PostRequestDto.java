package com.springboot.api.domain.dto;


import com.springboot.api.domain.Post;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter @Setter
public class PostRequestDto {

    private String title;
    private String writer;
    private String content;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private Integer viewCount;

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
