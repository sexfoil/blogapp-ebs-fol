package com.epam.blogappebsfol.controller;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getPosts();
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto post) {
        return postService.createPost(post);
    }

    @PatchMapping
    public PostDto updatePost(@RequestBody PostDto post) {
        return postService.updatePostTags(post);
    }

    @DeleteMapping
    public void deletePost(@RequestBody PostDto post) {
        postService.deleteById(post.getId());
    }
}
