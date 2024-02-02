package com.epam.blogappebsfol.controller;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("api/v1/posts")
@AllArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping
    public List<PostDto> getAllPosts() {
        return postService.getPosts();
    }

    @GetMapping("/tags")
    public List<PostDto> getAllPostsByTags(@RequestParam Set<String> tags) {
        return postService.getPostsByTags(tags);
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
