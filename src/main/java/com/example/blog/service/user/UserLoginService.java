package com.example.blog.service.user;

import com.example.blog.api.exception.InvalidAuthenticationException;
import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.responseDTO.UserLoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    private UserRepository userRepository;

    @Autowired
    public UserLoginService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public User loginUser(UserLoginData userLoginData) {
        User user = userRepository.findbyEmail(userLoginData.getEmail()).orElseThrow(InvalidAuthenticationException::new);
        if (user.getPassword().equals(userLoginData.getPassword())) {
            return user;
        }
        else{
            throw new InvalidAuthenticationException();
        }
    }
}
