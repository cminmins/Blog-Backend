package com.example.blog.api.user;

import com.example.blog.api.exception.InvalidAuthenticationException;
import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.requestDTO.RequestUserLogin;
import com.example.blog.service.requestDTO.RequestUserRegister;
import com.example.blog.service.responseDTO.UserData;
import com.example.blog.service.responseDTO.UserLoginData;
import com.example.blog.service.user.UserLoginService;
import com.example.blog.service.user.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserApi {
    private UserRepository userRepository;
    private UserRegisterService userRegisterService;
    private UserLoginService userLoginService;

    @Autowired
    public UserApi(UserRepository userRepository, UserRegisterService userRegisterService, UserLoginService userLoginService) {
        this.userRepository = userRepository;
        this.userRegisterService = userRegisterService;
        this.userLoginService = userLoginService;
    }

    private Map<String, Object> userResponse(UserData userData) {
        return new HashMap<String, Object>() {
            {
                put("user", userData);
            }
        };
    }

    @PostMapping("/users")
    public ResponseEntity createUser(@Valid @RequestBody RequestUserRegister requestUserRegister) {
        User user = userRegisterService.createUser(requestUserRegister);
        UserData userData = new UserData(user.getUsername(), user.getEmail(), user.getBio(), user.getImage());
        return ResponseEntity.status(201).body(userResponse(userData));
    }

    @PostMapping("/user/login")
    public ResponseEntity loginUser(@Valid @RequestBody RequestUserLogin requestUserLogin) {
        UserLoginData userLoginData = new UserLoginData(requestUserLogin.getEmail(), requestUserLogin.getPassword());
        User user = userLoginService.loginUser(userLoginData);
        if(user != null){
            UserData userData = new UserData(user.getUsername(), user.getEmail(), user.getBio(), user.getImage());
            return ResponseEntity.ok().body(userResponse(userData));
        }
        else{
            throw new InvalidAuthenticationException();
        }
    }
}
