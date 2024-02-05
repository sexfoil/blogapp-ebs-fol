package com.epam.blogappebsfol.domain.mapper;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto postEntityToDto(PostEntity entity);

    PostEntity postDtoToEntity(PostDto dto);

    default String fromTagEntity(TagEntity entity) {
        return entity.getValue();
    }

    default Set<String> toDtoSet(Set<TagEntity> entities) {
        return entities.stream()
                .map(TagEntity::getValue)
                .collect(Collectors.toSet());
    }

    default Set<TagEntity> toEntitySet(Set<String> entities) {
        return entities.stream()
                .map(v -> TagEntity.builder().value(v).build())
                .collect(Collectors.toSet());
    }
}
