package com.epam.blogappebsfol.domain.mapper;

import com.epam.blogappebsfol.domain.dto.TagDto;
import com.epam.blogappebsfol.domain.entity.TagEntity;
import org.mapstruct.*;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;
import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateTagDtoFromEntity(TagDto dto, @MappingTarget TagEntity entity);

    TagDto tagEntityToDto(TagEntity entity);

    TagEntity tagDtoToEntity(TagDto dto);

}
