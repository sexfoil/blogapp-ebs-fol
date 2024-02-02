package com.epam.blogappebsfol.domain.mapper;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import org.mapstruct.*;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updatePostDtoFromEntity(PostDto dto, @MappingTarget PostEntity entity);

    @Mapping(target = "tags", ignore = true)

    PostDto postEntityToDto(PostEntity entity);

    @Mapping(target = "tags", ignore = true)
    PostEntity postDtoToEntity(PostDto dto);

    default String fromTagEntity(TagEntity entity) {
        return entity.getValue();
    }
    default Set<String> toDtoSet(Set<TagEntity> entities) {
        return entities.stream()
                .map(TagEntity::getValue)
                .collect(Collectors.toSet());
    }
    default TagEntity toTagEntity(String value) {
        return TagEntity.builder().value("WTF").build();
    }
}
