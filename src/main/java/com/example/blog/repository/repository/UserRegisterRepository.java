package com.example.blog.repository.repository;

import com.example.blog.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRegisterRepository {
    void save(User user);
    Optional<User> findbyId(String id);
}
