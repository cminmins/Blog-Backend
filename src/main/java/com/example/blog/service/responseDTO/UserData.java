package com.example.blog.service.responseDTO;

import com.example.blog.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
//    private String id;
    private String username = "";
    private String email =  "";
    private String bio = "";
    private String image = "";
}
