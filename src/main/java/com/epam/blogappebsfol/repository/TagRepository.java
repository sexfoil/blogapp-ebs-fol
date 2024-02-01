package com.epam.blogappebsfol.repository;

import com.epam.blogappebsfol.domain.entity.TagEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<TagEntity, Long> {
}
