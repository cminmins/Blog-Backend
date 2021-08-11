package com.example.blog.service.requestDTO;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonRootName("user")
public class RequestUserLogin {

    @Email
    @NotBlank(message = "can't be empty")
    private String email;

    @NotBlank(message = "can't be empty")
    private String password;
}
