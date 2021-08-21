package com.example.blog.service.user;

import com.example.blog.api.exception.InvalidAuthenticationException;
import com.example.blog.api.user.UserDTOMapper;
import com.example.blog.domain.user.User;
import com.example.blog.repository.mybatis.mapper.user.MybatisUserRepository;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.requestDTO.RequestUserLogin;
import com.example.blog.service.responseDTO.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;

//@Testcontainers
@Import({MybatisUserRepository.class})
@Transactional
class UserLoginServiceTest {
    @Autowired
    private UserRepository userRepository;
    @Container
    private static final MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7.25");

    private UserDTOMapper userDTOMapper = Mappers.getMapper(UserDTOMapper.class);
    private User user;

    @BeforeEach
    void setUp() {
        user = new User("testaccount@test.com", "tester", "1234", "bio_test", "image_test");
    }

    @Test
    void loginUser() {
        RequestUserLogin correct_request = new RequestUserLogin("testaccount@test.com", "1234");
        RequestUserLogin wrong_email_request = new RequestUserLogin("tecount@test.com", "1234");
        RequestUserLogin wrong_password_request = new RequestUserLogin("testaccount@test.com", "14");


        User user = userRepository.findByEmail(correct_request.getEmail())
                .orElseThrow(() -> new InvalidAuthenticationException());
        if (user.isNotMatchPassword(correct_request.getPassword())) {
            throw new InvalidAuthenticationException();
        }
        UserData userData = userDTOMapper.entityToUserData(user);
    }
}