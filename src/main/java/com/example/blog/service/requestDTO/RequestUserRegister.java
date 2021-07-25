package com.example.blog.service.requestDTO;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@JsonRootName("user")
public class RequestUserRegister {

    @NotBlank(message = "Can't be empty")
    String username;

    @Email(message = "should be an email")
    @NotBlank(message = "Can't be empty")
    String email;

    @NotBlank(message = "Can't be empty")
    String password;
}
