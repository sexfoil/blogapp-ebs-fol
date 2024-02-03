package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.dto.TagDto;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import com.epam.blogappebsfol.domain.mapper.TagMapper;
import com.epam.blogappebsfol.repository.TagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
}
