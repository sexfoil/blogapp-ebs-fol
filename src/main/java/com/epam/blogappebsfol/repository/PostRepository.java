package com.epam.blogappebsfol.repository;

import com.epam.blogappebsfol.domain.entity.PostEntity;
import org.springframework.data.repository.CrudRepository;


public interface PostRepository extends CrudRepository<PostEntity, Long> {
//    List<PostEntity> findPostEntitiesByTagsContains(Set<TagEntity> tags);
}
