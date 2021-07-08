package com.example.blog.repository.mybatis;

import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRegisterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@Import(MybatisUserRegisterRepository.class)
class MybatisUserRegisterRepositoryTest extends MyBatisDBTest {
    @Autowired
    private UserRegisterRepository userRegisterRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User("cminmins@git.com", "cmin", "123", "", "default");
    }

    @Test
    void save() {
        userRegisterRepository.save(user);
        Optional<User> userOptional = userRegisterRepository.findbyId("cminmins");
        assertEquals(userOptional.get(), user);
    }

    @Test
    void findbyId() {
    }
}