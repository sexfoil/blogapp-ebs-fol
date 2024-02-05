package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import com.epam.blogappebsfol.domain.exception.PostDuplicateException;
import com.epam.blogappebsfol.domain.exception.PostNotFoundException;
import com.epam.blogappebsfol.domain.mapper.PostMapper;
import com.epam.blogappebsfol.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostService {

    private final PostRepository repository;
    private final PostMapper postMapper;

    private final TagService tagService;

    /**
     *  Returns list of posts. Limited by page size
     *
     * @param   pageNumber page number
     * @param   pageSize amount of posts per page
     * @return  the set of TagEntity just created
     */
    public List<PostDto> getPosts(int pageNumber, int pageSize) {
        Page<PostEntity> posts = repository.findAllPosts(PageRequest.of(pageNumber, pageSize));
        return posts.stream()
                .map(postMapper::postEntityToDto)
                .toList();
    }

    /**
     *  Returns list of posts with tags specified. Limited by page size
     *
     * @param   tags the set of tags to search
     * @param   pageNumber page number
     * @param   pageSize amount of posts per page
     * @return  the set of TagEntity just created
     */
    public List<PostDto> getPostsByTags(Set<String> tags, int pageNumber, int pageSize) {
        Set<PostEntity> posts = tagService.getPostsByTag(tags, pageNumber, pageSize);
        return posts.stream()
                .map(postMapper::postEntityToDto)
                .toList();
    }

    /**
     *  Returns created post
     *
     * @param   post the post data to create
     * @return  the post just created
     * @throws PostDuplicateException if post exists
     */
    @Transactional
    public PostDto createPost(PostDto post) {
        if (post.getId() != null) {
            throw new PostDuplicateException();
        }

        PostEntity postEntity = postMapper.postDtoToEntity(post);

        createTagIfNotExist(post.getTags());

        Set<TagEntity> tagEntities = getTagEntities(post.getTags());
        postEntity.setTags(tagEntities);

        PostEntity newPostEntity = repository.save(postEntity);
        PostDto newPostDto = postMapper.postEntityToDto(newPostEntity);

        newPostDto.setTags(postMapper.toDtoSet(tagEntities));

        log.info("Post with id: {} was successfully created.", newPostDto.getId());
        return newPostDto;
    }

    /**
     *  Returns post updated by tags
     *
     * @param   id the id of the post to update
     * @param   tags the tags to update
     * @return  the post just updated
     * @throws PostNotFoundException if post does not exist
     */
    @Transactional
    public PostDto updatePostTags(Long id, Set<String> tags) {
        PostEntity post = repository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id));

        createTagIfNotExist(tags);
        post.setTags(getTagEntities(tags));
        PostEntity updatedEntity = repository.save(post);

        PostDto updated = postMapper.postEntityToDto(updatedEntity);

        log.info("Post with id: {} was successfully updated.", id);
        return updated;
    }

    /**
     *  Delete the post
     *
     * @param   id the id of the post to delete
     */
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
        log.info("Post with id: {} was successfully deleted.", id);
    }

    private void createTagIfNotExist(Set<String> tags) {
        tagService.createTags(tags);
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
