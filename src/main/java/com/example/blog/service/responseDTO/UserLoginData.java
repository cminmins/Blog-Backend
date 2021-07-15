package com.example.blog.service.responseDTO;

import com.example.blog.domain.user.User;
import com.example.blog.service.requestDTO.RequestUserLogin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginData {
    private String email;
    private String password;


    @Autowired
    private ModelMapper modelMapper;

    // Entity -> DTO
    public UserLoginData of(User user){
        return modelMapper.map(user, UserLoginData.class);
    }

    // DTO -> Entity
    public User toEntity(){
        return Builder;
    }
}
