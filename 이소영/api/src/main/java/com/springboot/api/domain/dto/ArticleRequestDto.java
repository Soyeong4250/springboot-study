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
    private String writer;

    public ArticleRequestDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Article toEntity() {
        return new Article(this.title, this.content, this.writer);
    }
}
