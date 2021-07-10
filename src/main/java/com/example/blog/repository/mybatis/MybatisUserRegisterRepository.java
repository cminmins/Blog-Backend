package com.example.blog.repository.mybatis;

import com.example.blog.domain.user.User;
import com.example.blog.repository.mybatis.mapper.user.UserMapper;
import com.example.blog.repository.repository.UserRegisterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MybatisUserRegisterRepository implements UserRegisterRepository {
    private final UserMapper userMapper;

    @Autowired
    public MybatisUserRegisterRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public Optional<User> findbyId(String id) {
        return Optional.empty();
    }
}