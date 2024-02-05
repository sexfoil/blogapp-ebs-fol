package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.entity.TagEntity;
import com.epam.blogappebsfol.repository.TagRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.epam.blogappebsfol.service.TestDataProvider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TagServiceTest {

    @InjectMocks
    TagService subject;

    @Mock
    TagRepository repository;

    @Test
    void givenTags_whenCreateTags_shouldReturnNewTags() {
        when(repository.getAllTagValues()).thenReturn(EXISTING_TAGS);

        Set<TagEntity> createdTags = subject.createTags(TAGS_TO_CREATE);

        assertNotNull(createdTags);
        assertEquals(2, createdTags.size());
        assertTrue(createdTags.stream().map(TagEntity::getValue).collect(Collectors.toSet()).containsAll(NEW_TAGS));
    }
}
