package com.springboot.api.controller;

import com.springboot.api.domain.Post;
import com.springboot.api.domain.dto.PostRequestDto;
import com.springboot.api.domain.dto.PostResponseDto;
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
        return "redirect:/posts/list";
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
    public String createPostForm() {
        return "form/new";
    }

    @PostMapping("/new")
    public String createPost(PostRequestDto postRequestDto) {
        log.info("Controller write. writer={}, title={}", postRequestDto.getWriter(), postRequestDto.getTitle());
        PostResponseDto response = postService.createPost(postRequestDto);
        return "redirect:/posts/" + response.getId();
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable Long id, Model model) {
        Optional<Post> posts = postRepository.findById(id);
        if (!posts.isEmpty()) {
            postService.updateViewCount(id);
            model.addAttribute("posts", posts.get());
            return "form/read";
        } else {
            return "errors/error";
        }
    }

    @GetMapping("/{id}/update")
    public String updateForm(@PathVariable Long id, PostRequestDto postRequestDto, Model model) {
        Optional<Post> findPost = postRepository.findById(id);
        if (!findPost.isEmpty()) {
            model.addAttribute("posts", findPost.get());
            return "form/edit";
        } else {
            return "errors/error";
        }
    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable Long postId, PostRequestDto postRequestDto, Model model) {
        log.info("게시글 수정. writer={}, title={}", postRequestDto.getWriter(), postRequestDto.getTitle());
        Post savePost = postRepository.save(postRequestDto.toEntity());
        model.addAttribute("posts", savePost);
        return "redirect:/posts/" + savePost.getId();
    }

    @GetMapping("{id}/delete")
    public String deleteById(@PathVariable Long id) {
        postRepository.deleteById(id);
        return "redirect:/posts/list";
    }

    @GetMapping("/search")
    public String search(String keyword, Model model,
                         @PageableDefault(sort = "id", direction = Sort.Direction.DESC)Pageable pageable) {
        Page<Post> searchList = postService.search(pageable, keyword);
        model.addAttribute("searchList", searchList);
        model.addAttribute("keyword", keyword);
        model.addAttribute("previous", pageable.previousOrFirst());
        model.addAttribute("next", pageable.next());
        model.addAttribute("hasPrevious", searchList.hasPrevious());
        model.addAttribute("hasNext", searchList.hasNext());
        return "form/search";
    }
}
