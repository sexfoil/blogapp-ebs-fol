package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.dto.TagDto;
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

    public Set<PostEntity> getPostsByTag(Set<String> dtos) {
        List<TagEntity> allByValue = repository.findAllByValueIn(dtos);
        return allByValue.stream()
                .flatMap(tagEntity -> tagEntity.getPosts().stream())
                .collect(Collectors.toSet());
    }

    public TagEntity getTagByValue(String value) {
        return repository.findByValue(value);
    }
}
