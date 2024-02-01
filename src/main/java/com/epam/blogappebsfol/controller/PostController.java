package com.epam.blogappebsfol.controller;

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
    public List<PostEntity> getAllPosts() {
        return postService.getPosts();
    }

    @PostMapping
    public PostEntity createPost(@RequestBody PostEntity post) {
        return postService.createPost(post);
    }

    @PatchMapping
    public PostEntity updatePost(@RequestBody PostEntity post) {
        return postService.updatePostTags(post);
    }

    @DeleteMapping
    public void deletePost(@RequestBody PostEntity post) {
        postService.deleteById(post.getId());
    }
}
