package com.example.blog.repository.mybatis;

import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(MybatisUserRepository.class)
class MybatisUserApiRepositoryTest extends MyBatisDBTest {
    @Autowired
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("cminmins@git.com", "cmin", "123", "", "default");
    }

    @Test
    void save() {
        userRepository.save(user);
        Optional<User> userOptional = userRepository.findByEmail("cminmins");
        assertEquals(userOptional.get(), user);
    }

    @Test
    void findbyId() {
    }
}