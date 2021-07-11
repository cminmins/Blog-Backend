package com.example.blog.api.user;

import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.UserRegisterRepository;
import com.example.blog.service.requestBody.RequestUserRegister;
import com.example.blog.service.responseData.UserData;
import com.example.blog.service.user.UserRegisterService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.DatatypeConverter;
import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class UserRegister {
    private UserRegisterRepository userRegisterRepository;
    private UserRegisterService userRegisterService;

    @Autowired
    public UserRegister(UserRegisterRepository userRegisterRepository, UserRegisterService userRegisterService) {
        this.userRegisterRepository = userRegisterRepository;
        this.userRegisterService = userRegisterService;
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

}
