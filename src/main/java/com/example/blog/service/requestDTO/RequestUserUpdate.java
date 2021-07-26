package com.example.blog.service.requestDTO;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@JsonRootName("user")
public class RequestUserUpdate {
    @Email(message = "should be an email")
    private String email;
    private String username;
    private String password;
    private String bio;
    private String image;
}
