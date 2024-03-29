package com.epam.blogappebsfol.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    Long id;

    @Column(name = "tag_value", unique = true, nullable = false)
    String value;

    @ManyToMany(mappedBy = "tags", fetch = FetchType.EAGER)
    @Column(name = "posts")
    Set<PostEntity> posts = new HashSet<>();
}
