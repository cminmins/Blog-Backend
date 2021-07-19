package com.example.blog.service.requestDTO;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@JsonRootName("user")
@AllArgsConstructor
@NoArgsConstructor
public class RequestUserLogin {

    @Email
    @NotBlank(message = "Can't be empty")
    private String email;

    @NotBlank(message = "Can't be empty")
    private String password;
}