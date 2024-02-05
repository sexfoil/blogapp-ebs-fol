package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.domain.entity.PostEntity;
import com.epam.blogappebsfol.domain.entity.TagEntity;

import java.util.List;
import java.util.Set;

public class TestDataProvider {

    public static final List<String> EXISTING_TAGS = List.of("tag_1", "tag_2", "tag_3");
    public static final List<String> NEW_TAGS = List.of("tag_4", "tag_5");
    public static final Set<String> TAGS_TO_CREATE = Set.of("tag_1", "tag_4", "tag_5");

    public static final PostDto POST_DTO = PostDto.builder()
            .title("post")
            .content("post content")
            .tags(Set.of("tag"))
            .build();

    public static final PostDto SAVED_POST_DTO = PostDto.builder()
            .id(1L)
            .title("post")
            .content("post content")
            .tags(Set.of("tag"))
            .build();

    public static final TagEntity TAG_ENTITY = TagEntity.builder()
            .id(1L)
            .value("tag")
            .build();

    public static final PostEntity POST_ENTITY = PostEntity.builder()
            .title("post")
            .content("post content")
            .tags(Set.of(TAG_ENTITY))
            .build();

    public static final PostEntity SAVED_POST_ENTITY = PostEntity.builder()
            .id(1L)
            .title("post")
            .content("post content")
            .tags(Set.of(TAG_ENTITY))
            .build();

}
