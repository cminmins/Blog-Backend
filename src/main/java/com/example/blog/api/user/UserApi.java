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
import org.modelmapper.ModelMapper;
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
        UserData userData = userRegisterService.createUser(requestUserRegister);
        return ResponseEntity.status(201).body(userResponse(userData));
    }

    @PostMapping("/user/login")
    public ResponseEntity loginUser(@Valid @RequestBody RequestUserLogin requestUserLogin) {
        UserData userData = userLoginService.loginUser(requestUserLogin);
        return ResponseEntity.ok().body(userResponse(userData));
    }
}
