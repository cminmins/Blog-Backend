package com.example.blog.service.user;

import com.example.blog.api.exception.InvalidAuthenticationException;
import com.example.blog.api.user.UserDTOMapper;
import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.responseDTO.UserData;
import com.example.blog.service.responseDTO.UserLoginData;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLoginService {
    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Autowired
    public UserLoginService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public UserData loginUser(UserLoginData userLoginData) {
        User user = userRepository.findbyEmail(userLoginData.getEmail())
                .orElseThrow(() -> new InvalidAuthenticationException());
        if (user.isNotMatchPassword(userLoginData.getPassword())) {
            throw new InvalidAuthenticationException();
        }
        return modelMapper.map(user, UserData.class);
//        return UserDTOMapper.INSTANCE.entityToUserData(user);
    }
}
