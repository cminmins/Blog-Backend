package com.example.blog.service.responseDTO;

import com.example.blog.domain.user.User;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Getter
@Setter
public class UserLoginData {
    private String email;
    private String password;

    public UserLoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
