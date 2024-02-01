package com.epam.blogappebsfol.repository;

import com.epam.blogappebsfol.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
}
