package com.epam.blogappebsfol.domain.dto;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Set;

import static lombok.AccessLevel.PRIVATE;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = PRIVATE)
public class PostDto {

    @Nullable
    private Long id;

//    @JsonProperty("title")
    private String title;

//    @JsonProperty("content")
    private String content;

//    @JsonProperty("tags")
    @Nullable
    private Set<TagDto> tags;
}
