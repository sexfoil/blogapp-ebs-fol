package com.epam.blogappebsfol.service;

import com.epam.blogappebsfol.domain.dto.PostDto;
import com.epam.blogappebsfol.domain.exception.PostDuplicateException;
import com.epam.blogappebsfol.domain.exception.PostNotFoundException;
import com.epam.blogappebsfol.domain.mapper.PostMapper;
import com.epam.blogappebsfol.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static com.epam.blogappebsfol.service.TestDataProvider.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PostServiceTest {

    @InjectMocks
    PostService subject;

    @Mock
    PostRepository repository;
    @Mock
    PostMapper postMapper;
    @Mock
    TagService tagService;

    @Test
    void givenPost_whenCreatePost_shouldReturnNewPost() {
        when(postMapper.postDtoToEntity(POST_DTO)).thenReturn(POST_ENTITY);
        when(repository.save(POST_ENTITY)).thenReturn(SAVED_POST_ENTITY);
        when(postMapper.postEntityToDto(SAVED_POST_ENTITY)).thenReturn(SAVED_POST_DTO);

        subject.createPost(POST_DTO);

        verify(postMapper, times(1)).toDtoSet(any());
        verify(postMapper, times(1)).postDtoToEntity(any());
        verify(postMapper, times(1)).postEntityToDto(any());
    }

    @Test
    void givenExistingPost_whenCreatePost_shouldThrowException(){
        Exception ex = assertThrows(PostDuplicateException.class, () -> subject.createPost(SAVED_POST_DTO));
        assertEquals("Post with such id already exists", ex.getMessage());
    }

    @Test
    void givenPost_whenUpdatePost_shouldReturnUpdatedPost() {
        when(repository.findById(any())).thenReturn(Optional.of(SAVED_POST_ENTITY));

        subject.updatePostTags(1L, Set.of("tag_12"));

        verify(repository, times(1)).save(SAVED_POST_ENTITY);
        verify(postMapper, times(1)).postEntityToDto(any());
    }

    @Test
    void givenNotExistingPost_whenUpdatePost_shouldThrowException(){
        long id = 222;
        String expectedMessage = String.format("Post with id: %s does not exist", id);

        when(repository.findById(any())).thenReturn(Optional.empty());

        Exception ex = assertThrows(PostNotFoundException.class, () -> subject.updatePostTags(id, Set.of()));

        assertEquals(expectedMessage, ex.getMessage());
    }
}
