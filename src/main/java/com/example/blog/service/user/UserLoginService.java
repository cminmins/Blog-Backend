package com.example.blog.service.user;

import com.example.blog.api.user.UserDTOMapper;
import com.example.blog.domain.user.User;
import com.example.blog.exception.InvalidAuthenticationException;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.requestDTO.RequestUserLogin;
import com.example.blog.service.responseDTO.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserLoginService {
    private UserRepository userRepository;
    private UserDTOMapper userDTOMapper;

    @Autowired
    public UserLoginService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public UserData loginUser(@Valid RequestUserLogin requestUserLogin) {
        User user = userRepository.findByEmail(requestUserLogin.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException());
        if (user.isNotMatchPassword(requestUserLogin.getPassword())) {
            throw new InvalidAuthenticationException();
        }
        return userDTOMapper.entityToUserData(user);
    }
}
