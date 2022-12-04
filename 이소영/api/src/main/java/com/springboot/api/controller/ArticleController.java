package com.springboot.api.controller;

import com.springboot.api.domain.dto.ArticleRequestDto;
import com.springboot.api.domain.entity.Article;
import com.springboot.api.repository.ArticleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

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
        log.info("generatedId : {}", savedArticle.getArticleId());
        return "redirect:/articles/list";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<Article> articleList = articleRepository.findAll();
        log.info("articleList size:{}", articleList.size());
        model.addAttribute("articles", articleList);
        return "articles/list";
    }

    @GetMapping("/{articleId}")
    public String getArticle(@PathVariable Long articleId,  Model model) {
        Optional<Article> optArticle = articleRepository.findById(articleId);
        if(!optArticle.isEmpty()) {
            model.addAttribute("article", optArticle.get());
            return "articles/view";
        }else {
            model.addAttribute("message", String.format("%d번 게시글이 존재하지 않습니다", articleId));
            return "error";
        }
    }
}
