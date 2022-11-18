package com.springboot.api.controller;

import com.springboot.api.domain.dto.ArticleRequestDto;
import com.springboot.api.domain.entity.Article;
import com.springboot.api.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
@RequestMapping("/articles")
public class ArticleController {

    private final ArticleRepository articleRepository;

    public ArticleController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping("/new")
    public String registerPage() {
        return "articles/register";
    }

    @PostMapping()
    public String register(ArticleRequestDto articleRequestDto) {
        log.info(articleRequestDto.toString());
        Article savedArticle = articleRepository.save(articleRequestDto.toEntity());
        log.info("generatedId : {}", savedArticle.getId());
        return "";
    }
}
