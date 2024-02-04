package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import com.epam.blogappebsfol.domain.mapper.TagMapper;
import com.epam.blogappebsfol.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository repository;
    private final TagMapper mapper;

    public Set<PostEntity> getPostsByTag(Set<String> tags, int pageNumber, int pageSize) {
        int pagesToSkip = pageNumber * pageSize;
        List<TagEntity> tagEntities = repository.findAllPostsByTags(tags);
        return tagEntities.stream()
                .flatMap(tagEntity -> tagEntity.getPosts().stream())
                .skip(pagesToSkip)
                .limit(pageSize)
                .collect(Collectors.toSet());
    }

    public TagEntity getTagByValue(String value) {
        return repository.findByValue(value);
    }

    public List<TagEntity> createTags(Set<String> tags) {
        List<String> existingTags = repository.getAllTagValues();
        Set<TagEntity> tagEntities = tags.stream()
                .filter(tag -> !existingTags.contains(tag))
                .map(tag -> TagEntity.builder().value(tag).posts(Set.of()).build())
                .collect(Collectors.toSet());
        return repository.saveAll(tagEntities);
    }
}
