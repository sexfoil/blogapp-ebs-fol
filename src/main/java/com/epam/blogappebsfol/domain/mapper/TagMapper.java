package com.epam.blogappebsfol.domain.mapper;

import com.epam.blogappebsfol.domain.dto.TagDto;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring")
public interface TagMapper {

    TagDto tagEntityToDto(TagEntity entity);

    @Mapping(target = "posts", ignore = true)
    TagEntity tagDtoToEntity(TagDto dto);

    default Set<String> toDtoSet(Set<TagEntity> entities) {
        return entities.stream()
                .map(TagEntity::getValue)
                .collect(Collectors.toSet());
    }

}
