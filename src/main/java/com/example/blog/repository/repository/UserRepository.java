package com.example.blog.repository.repository;

import com.example.blog.domain.user.User;
import com.example.blog.service.requestDTO.RequestUserUpdate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    void save(User user);
    void update(User user);

    Optional<User> findByEmail(String email);
    Optional<User> findById(String id);
    Optional<User> findByUsername(String username);
}
