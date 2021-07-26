package com.example.blog.service.user;

import com.example.blog.api.user.UserDTOMapper;
import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.requestDTO.RequestUserUpdate;
import com.example.blog.service.responseDTO.UserData;
import org.springframework.stereotype.Service;

@Service
public class UserUpdateService {
    private UserRepository userRepository;
    private UserDTOMapper userDTOMapper;

    public UserUpdateService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public UserData updateUser(String id, RequestUserUpdate requestUserUpdate) {
        User user = userRepository.findById(id).orElseGet(null);
        user.update(requestUserUpdate.getEmail(),
                requestUserUpdate.getUsername(),
                requestUserUpdate.getPassword(),
                requestUserUpdate.getBio(),
                requestUserUpdate.getImage());
        userRepository.update(user);

        return userDTOMapper.entityToUserData(user);
    }
}
