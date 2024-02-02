package com.epam.blogappebsfol.repository;

import com.epam.blogappebsfol.domain.entity.TagEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface TagRepository extends CrudRepository<TagEntity, Long> {

//    @Query(value = "SELECT * FROM tags WHERE value = ?1", nativeQuery = true)
    TagEntity findByValue(String value);

    List<TagEntity> findAllByValueIn(Set<String> values);
}
