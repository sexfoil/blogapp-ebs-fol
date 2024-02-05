package com.epam.blogappebsfol.domain.mapper;

import com.epam.blogappebsfol.domain.dto.TagDto;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto tagEntityToDto(TagEntity entity);

    TagEntity tagDtoToEntity(TagDto dto);

    default Set<String> toDtoSet(Set<TagEntity> entities) {
        return entities.stream()
                .map(TagEntity::getValue)
                .collect(Collectors.toSet());
    }

    default Set<TagEntity> toEntitySet(Set<String> tags) {
        return tags.stream()
                .map(v -> TagEntity.builder().value(v).build())
                .collect(Collectors.toSet());
    }
}
