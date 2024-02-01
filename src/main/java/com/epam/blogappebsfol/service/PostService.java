package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.dto.Post;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<PostEntity> getPosts() {
        return postRepository.findAll();
    }

    public PostEntity createPost(PostEntity post) {
        return postRepository.save(post);
    }

    public PostEntity updatePostTags(PostEntity post) {
        return postRepository.save(post);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }
}
