package com.example.blog.repository.repository;

import com.example.blog.domain.article.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagsRepository {
    Optional<List> findAllTags();
}
