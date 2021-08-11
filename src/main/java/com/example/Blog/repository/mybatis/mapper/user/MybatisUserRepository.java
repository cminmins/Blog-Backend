package com.example.blog.repository.mybatis.mapper.user;

import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MybatisUserRepository implements UserRepository {
    private final UserMapper userMapper;

    @Autowired
    public MybatisUserRepository(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {
        userMapper.insert(user);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(this.userMapper.findByEmail(email));
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.ofNullable(this.userMapper.findById(id));
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.ofNullable(this.userMapper.findByUsername(username));
    }
}
