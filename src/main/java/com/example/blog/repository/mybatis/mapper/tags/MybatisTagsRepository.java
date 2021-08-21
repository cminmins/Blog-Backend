package com.example.blog.repository.mybatis.mapper.tags;

import com.example.blog.repository.repository.TagsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MybatisTagsRepository implements TagsRepository {
    private TagsMapper tagsMapper;

    public MybatisTagsRepository(TagsMapper tagsMapper) {
        this.tagsMapper = tagsMapper;
    }

    @Override
    public Optional<List> findAllTags() {
        return Optional.ofNullable(tagsMapper.findAllTags());
    }
}
