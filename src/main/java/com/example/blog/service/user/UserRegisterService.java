package com.example.blog.service.user;

import com.example.blog.api.user.UserDTOMapper;
import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.requestDTO.RequestUserRegister;
import com.example.blog.service.responseDTO.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Service
public class UserRegisterService {
    private UserRepository userRepository;
    private UserDTOMapper userDTOMapper;
    @Value("${default.image}")
    private String image;

    public UserRegisterService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public UserData createUser(@Valid RequestUserRegister requestUserRegister) {
        User user = userDTOMapper.dtoToUser(requestUserRegister);
        user.setImage(image);
        userRepository.save(user);
        return userDTOMapper.entityToUserData(user);
    }
}
