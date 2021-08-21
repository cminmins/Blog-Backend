package com.example.blog.repository.mybatis.mapper.tags;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TagsMapper {
    List<String> findAllTags();
}
