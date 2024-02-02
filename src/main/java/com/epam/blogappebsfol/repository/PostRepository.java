package com.epam.blogappebsfol.repository;

import com.epam.blogappebsfol.domain.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

//public interface PostRepository extends  JpaRepository<PostEntity, Long> {
public interface PostRepository extends CrudRepository<PostEntity, Long> {


}
