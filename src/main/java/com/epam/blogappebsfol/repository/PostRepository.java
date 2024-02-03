package com.epam.blogappebsfol.repository;

import com.epam.blogappebsfol.domain.entity.PostEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;


public interface PostRepository extends JpaRepository<PostEntity, Long> {
    @Query(value = "SELECT * FROM posts", nativeQuery = true)
    Page<PostEntity> findAllPosts(Pageable pageable);

//    @Query(value = "SELECT * FROM posts WHERE tag_value IN (?1)", nativeQuery = true)
//    Page<PostEntity> findAllPostsByTags(Set<String> values, Pageable pageable);
}
