package com.epam.blogappebsfol.repository;

import com.epam.blogappebsfol.domain.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface TagRepository extends JpaRepository<TagEntity, Long> {

    @Query(value = "SELECT * FROM tags WHERE tag_value = ?1", nativeQuery = true)
    TagEntity findByValue(String value);

    @Query(value = "SELECT * FROM tags WHERE tag_value IN (?1)", nativeQuery = true)
    List<TagEntity> findAllPostsByTags(Set<String> values);
}
