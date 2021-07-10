package com.example.blog.service.user;

import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRegisterRepository;
import com.example.blog.service.requestBody.RequestUserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserRegisterService {
    private UserRegisterRepository userRegisterRepository;
    private String defaultImage;

    @Autowired
    public UserRegisterService(UserRegisterRepository userRegisterRepository,
                               @Value("${default.image}") String defaultImage) {
        this.userRegisterRepository = userRegisterRepository;
        this.defaultImage = defaultImage;
    }


    public User createUser(@Valid RequestUserRegister requestUserRegister) {
        User user = new User(requestUserRegister.getEmail(), requestUserRegister.getUsername(), requestUserRegister.getPassword(), "", defaultImage);
        userRegisterRepository.save(user);
        return user;
    }
}
