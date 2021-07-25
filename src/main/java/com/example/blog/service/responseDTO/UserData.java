package com.example.blog.service.responseDTO;

import com.example.blog.domain.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@Setter
public class UserData {
    private String id;
    private String username;
    private String email;
    private String bio;
    private String image;
}
