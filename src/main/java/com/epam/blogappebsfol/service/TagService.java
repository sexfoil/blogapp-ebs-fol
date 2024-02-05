package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import com.epam.blogappebsfol.domain.mapper.TagMapper;
import com.epam.blogappebsfol.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TagService {

    private final TagRepository repository;
    private final TagMapper mapper;

    /**
     *  Returns the set of posts with specified tags. Limited by page size.
     *
     * @param   tags the set of tags to search
     * @param   pageNumber page number
     * @param   pageSize amount of tags per page
     * @return  the set of posts with specified tags
     */
    public Set<PostEntity> getPostsByTag(Set<String> tags, int pageNumber, int pageSize) {
        int pagesToSkip = pageNumber * pageSize;
        List<TagEntity> tagEntities = repository.findAllPostsByTags(tags);
        Set<PostEntity> allPosts = tagEntities.stream()
                .flatMap(tagEntity -> tagEntity.getPosts().stream())
                .collect(Collectors.toSet());

        return allPosts.stream()
                .sorted(Comparator.comparing(PostEntity::getTitle))
                .skip(pagesToSkip)
                .limit(pageSize)
                .collect(Collectors.toSet());
    }

    /**
     *  Returns tag entity
     *
     * @param   value the value of tag
     * @return  the TagEntity with specified value
     */
    public TagEntity getTagByValue(String value) {
        return repository.findByValue(value);
    }

    /**
     *  Returns set of created tags
     *
     * @param   tags the set of tags to create
     * @return  the set of TagEntity just created
     */
    @Transactional
    public Set<TagEntity> createTags(Set<String> tags) {
        List<String> existingTags = repository.getAllTagValues();
        Set<TagEntity> tagEntities = tags.stream()
                .filter(tag -> !existingTags.contains(tag))
                .map(tag -> TagEntity.builder().value(tag).posts(Set.of()).build())
                .collect(Collectors.toSet());
        repository.saveAll(tagEntities);

        log.info("Tags were successfully created.");
        return tagEntities;
    }
}
