package com.springboot.api.controller;

import com.springboot.api.domain.Post;
import com.springboot.api.dto.PostRequestDto;
import com.springboot.api.repository.PostRepository;
import com.springboot.api.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;
    private final PostRepository postRepository;

    @GetMapping("")
    public String index() {
        return "redirect:/form/posts";
    }

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Post> posts = postService.pageList(pageable);
        model.addAttribute("posts", posts);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasPrev", posts.hasPrevious());
        model.addAttribute("hasNext", posts.hasNext());
        return "form/posts";
    }


    @GetMapping("/new")
    public String writeForm() {
        return "form/new";
    }

    @PostMapping("/new")
    public String write(PostRequestDto postRequestDto) {
        log.info("Controller write. writer={}, title", postRequestDto.getWriter(), postRequestDto.getTitle());
        Post post = postRequestDto.toEntity();
        postRepository.save(post);
        return "redirect:/posts/" + post.getId();
    }

    @GetMapping("/list/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Post> posts = postRepository.findById(id);
        if (!posts.isEmpty()) {
            model.addAttribute("posts", posts.get());
            return "form/read";
        } else {
            return "errors/error";
        }
    }

    @GetMapping("/list/{id}/update")
    public String updateForm(@PathVariable Long id, PostRequestDto postRequestDto, Model model) {
        Optional<Post> findPost = postRepository.findById(id);
        if (!findPost.isEmpty()) {
            model.addAttribute("posts", findPost.get());
            return "form/edit";
        } else {
            return "errors/error";
        }
    }

    @PostMapping("/list/{id}/update")
    public String update(PostRequestDto postRequestDto, Model model) {
        log.info("게시글 수정. writer={}, title={}", postRequestDto.getWriter(), postRequestDto.getTitle());
        Post savePost = postRepository.save(postRequestDto.toEntity());
        model.addAttribute("posts", savePost);
        return "redirect:/posts/" + savePost.getId();
    }

    @GetMapping("/list{id}/delete")
    public String deleteById(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/posts/list";
    }
}
