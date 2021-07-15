package com.example.blog.service.responseDTO;

import com.example.blog.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class UserLoginData {
    private String email;
    private String password;

    public UserLoginData(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
