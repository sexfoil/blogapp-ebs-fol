package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import com.epam.blogappebsfol.domain.mapper.PostMapper;
import com.epam.blogappebsfol.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    public List<PostDto> getPosts(int pageNumber, int pageSize) {
        Page<PostEntity> posts = repository.findAllPosts(PageRequest.of(pageNumber, pageSize));
        return posts.stream()
                .map(postMapper::postEntityToDto)
                .toList();
    }

    public List<PostDto> getPostsByTags(Set<String> tags, int pageNumber, int pageSize) {
        Set<PostEntity> posts = tagService.getPostsByTag(tags, pageNumber, pageSize);
        return posts.stream()
                .map(postMapper::postEntityToDto)
                .toList();
    }

    public PostDto createPost(PostDto post) {
        PostEntity postEntity = postMapper.postDtoToEntity(post);

        Set<TagEntity> tagEntities = getTagEntities(post.getTags());
        postEntity.setTags(tagEntities);

        PostEntity newPostEntity = repository.save(postEntity);
        PostDto created = postMapper.postEntityToDto(newPostEntity);

        created.setTags(postMapper.toDtoSet(tagEntities));

        return created;
    }

    public PostDto updatePostTags(Long id, Set<String> tags) {
        PostEntity post = repository.findById(id).orElseThrow(); // will be handled later
        tagService.createTags(tags);
        post.setTags(getTagEntities(tags));
        PostEntity updatedEntity = repository.save(post);
        return postMapper.postEntityToDto(updatedEntity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private Set<TagEntity> getTagEntities(Set<String> tags) {
        Set<TagEntity> tagEntities = new HashSet<>();
        if (tags != null) {
            tagEntities = tags.stream()
                    .map(tagService::getTagByValue)
                    .collect(Collectors.toSet());
        }
        return tagEntities;

    }
}
