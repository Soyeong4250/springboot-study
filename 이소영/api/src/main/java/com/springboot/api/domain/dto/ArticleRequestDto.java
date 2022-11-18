package com.springboot.api.domain.dto;

import com.springboot.api.domain.entity.Article;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ArticleRequestDto {
    private String title;
    private String content;

    public ArticleRequestDto(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        return new Article(this.title, this.content);
    }
}
