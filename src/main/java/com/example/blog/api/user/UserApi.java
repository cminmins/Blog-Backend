package com.example.blog.api.user;

import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.requestDTO.RequestUserLogin;
import com.example.blog.service.requestDTO.RequestUserRegister;
import com.example.blog.service.requestDTO.RequestUserUpdate;
import com.example.blog.service.responseDTO.UserData;
import com.example.blog.service.responseDTO.UserDataWithToken;
import com.example.blog.service.security.JwtTokenProvider;
import com.example.blog.service.user.UserCurrentService;
import com.example.blog.service.user.UserLoginService;
import com.example.blog.service.user.UserRegisterService;
import com.example.blog.service.user.UserUpdateService;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserApi {
    private UserRegisterService userRegisterService;
    private UserLoginService userLoginService;
    private UserCurrentService userCurrentService;
    private UserUpdateService userUpdateService;
    private JwtTokenProvider jwtTokenProvider;

    public UserApi(UserRegisterService userRegisterService, UserLoginService userLoginService, UserCurrentService userCurrentService, UserUpdateService userUpdateService, JwtTokenProvider jwtTokenProvider) {
        this.userRegisterService = userRegisterService;
        this.userLoginService = userLoginService;
        this.userCurrentService = userCurrentService;
        this.userUpdateService = userUpdateService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    private Map<String, Object> userResponse(UserDataWithToken userData) {
        return new HashMap<String, Object>() {
            {
                put("user", userData);
            }
        };
    }

    @PostMapping(value = "/users")
    public ResponseEntity createUser(@Valid @RequestBody RequestUserRegister requestUserRegister) {
        UserData userData = userRegisterService.createUser(requestUserRegister);
        return ResponseEntity.status(201)
                .body(userResponse(new UserDataWithToken(userData, jwtTokenProvider.buildToken(userData))));
    }

    @PostMapping("/users/login")
    public ResponseEntity loginUser(@Valid @RequestBody RequestUserLogin requestUserLogin) {
        UserData userData = userLoginService.loginUser(requestUserLogin);
        return ResponseEntity.ok().body(userResponse(new UserDataWithToken(userData, jwtTokenProvider.buildToken(userData))));
    }

    @GetMapping("/user")
    public ResponseEntity currentUser(@AuthenticationPrincipal User user,
                                      @RequestHeader(value = "Authorization", required = false) String authorization) {
        UserData userData = userCurrentService.getCurrentUser(user.getId());
        return ResponseEntity.ok().body(userResponse(new UserDataWithToken(userData, jwtTokenProvider.getTokenFromHeader(authorization))));
    }

    @PutMapping("/user")
    public ResponseEntity updateUser(@AuthenticationPrincipal User user,
                                     @RequestHeader(value = "Authorization", required = false) String authorization,
                                     @Valid @RequestBody RequestUserUpdate requestUserUpdate) {
        UserData userData = userUpdateService.updateUser(user.getId(), requestUserUpdate);
        return ResponseEntity.ok().body(userResponse(new UserDataWithToken(userData, jwtTokenProvider.getTokenFromHeader(authorization))));
    }
}
