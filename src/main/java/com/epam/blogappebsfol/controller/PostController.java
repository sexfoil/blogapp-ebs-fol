package com.epam.blogappebsfol.controller;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        return postService.getPosts(pageNumber, pageSize);
    }

    @GetMapping("/filter")
    public List<PostDto> getAllPostsByTags(
            @RequestParam Set<String> tags,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "20") Integer pageSize) {
        return postService.getPostsByTags(tags, pageNumber, pageSize);
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostDto post) {
        return postService.createPost(post);
    }

    @PatchMapping("/{id}")
    public PostDto updatePost(@PathVariable Long id, @RequestBody String[] tags) {
        return postService.updatePostTags(id, Set.of(tags));
    }

    @DeleteMapping
    public void deletePost(@RequestBody PostDto post) {
        postService.deleteById(post.getId());
    }
}
