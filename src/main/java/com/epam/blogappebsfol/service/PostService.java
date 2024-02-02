package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;

    public List<PostDto> getPosts() {
        List<PostEntity> posts = (List<PostEntity>) postRepository.findAll();
        return posts.stream()
                .map(this::entityToDto)
                .toList();
    }

    public PostDto createPost(PostDto post) {
        PostEntity newPostEntity = postRepository.save(dtoToEntity(post));
        return entityToDto(newPostEntity);
    }

    public PostDto updatePostTags(PostDto post) {
        Optional<PostEntity> entity = postRepository.findById(post.getId());
        PostEntity updatedPostEntity = postRepository.save(dtoToEntity(post));
        return entityToDto(updatedPostEntity);
    }

    public void deleteById(Long id) {
        postRepository.deleteById(id);
    }

    private PostDto entityToDto(PostEntity entity) {
        return mapper.map(entity, PostDto.class);
    }

    private PostEntity dtoToEntity(PostDto dto) {
        return mapper.map(dto, PostEntity.class);
    }
}
