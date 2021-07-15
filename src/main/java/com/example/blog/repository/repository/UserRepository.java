package com.example.blog.repository.repository;

import com.example.blog.domain.user.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    void save(User user);

    Optional<User> findbyEmail(String id);
}
