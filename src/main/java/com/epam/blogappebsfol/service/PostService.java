package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import com.epam.blogappebsfol.domain.mapper.PostMapper;
import com.epam.blogappebsfol.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repository;
    private final PostMapper postMapper;

    private final TagService tagService;

    public List<PostDto> getPosts() {
        List<PostEntity> posts = (List<PostEntity>) repository.findAll();
        return posts.stream()
                .map(postMapper::postEntityToDto)
                .toList();
    }

    public List<PostDto> getPostsByTags(Set<String> tags) {
        Set<PostEntity> posts = tagService.getPostsByTag(tags);
        return posts.stream()
                .map(postMapper::postEntityToDto)
                .toList();
    }

    public PostDto createPost(PostDto post) {
        PostEntity postEntity = postMapper.postDtoToEntity(post);

        Set<TagEntity> tags = new HashSet<>();
        if (post.getTags() != null) {
            tags = post.getTags().stream()
                    .map(tagService::getTagByValue)
                    .collect(Collectors.toSet());
        }

        postEntity.setTags(tags);
        PostEntity newPostEntity = repository.save(postEntity);
        PostDto created = postMapper.postEntityToDto(newPostEntity);
        created.setTags(postMapper.toDtoSet(tags));

        return created;
    }

    public PostDto updatePostTags(PostDto postDto) {
        assert postDto.getId() != null;
        PostEntity entity = repository.findById(postDto.getId()).orElseThrow();
        postMapper.updatePostDtoFromEntity(postDto, entity);
        PostEntity updatedEntity = repository.save(entity);
        return postMapper.postEntityToDto(updatedEntity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
