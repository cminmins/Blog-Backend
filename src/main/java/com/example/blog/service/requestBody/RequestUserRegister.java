package com.example.blog.service.requestBody;

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
public class RequestUserRegister {

    @NotBlank(message = "Can't be empty")
    String username;

    @Email(message = "should be an email")
    @NotBlank(message = "Can't be empty")
    String email;

    @NotBlank(message = "Can't be empty")
    String password;

}
