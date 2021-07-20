package com.example.blog.repository.mybatis.mapper.user;

import com.example.blog.domain.user.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    void insert(@Param("user") User user);
    User findByEmail(@Param("email") String email);
    User findById(@Param("id") String id);
}
