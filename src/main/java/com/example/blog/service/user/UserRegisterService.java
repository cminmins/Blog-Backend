package com.example.blog.service.user;

import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.requestDTO.RequestUserRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserRegisterService {
    private UserRepository userRepository;
    private String defaultImage;

    @Autowired
    public UserRegisterService(UserRepository userRepository,
                               @Value("${default.image}") String defaultImage) {
        this.userRepository = userRepository;
        this.defaultImage = defaultImage;
    }


    public User createUser(@Valid RequestUserRegister requestUserRegister) {
        User user = new User(requestUserRegister.getEmail(), requestUserRegister.getUsername(), requestUserRegister.getPassword(), "", defaultImage);
        userRepository.save(user);
        return user;
    }
}
