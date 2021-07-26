package com.example.blog.service.user;

import com.example.blog.api.user.UserDTOMapper;
import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.responseDTO.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCurrentService {
    private UserRepository userRepository;
    private UserDTOMapper userDTOMapper;

    public UserCurrentService(UserRepository userRepository, UserDTOMapper userDTOMapper) {
        this.userRepository = userRepository;
        this.userDTOMapper = userDTOMapper;
    }

    public UserData getCurrentUser(String id) {
        return userRepository.findById(id)
                .map(user -> userDTOMapper.entityToUserData(user))
                .orElseGet(null);
    }
}
