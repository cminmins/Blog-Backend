package com.example.blog.api.user;

import com.example.blog.service.responseData.UserData;
import lombok.Getter;

@Getter
public class UserToken {
    private String email;
    private String username;
    private String bio;
    private String image;
    private String token;

    public UserToken(UserData userData, String token) {
        this.email = userData.getEmail();
        this.username = userData.getUsername();
        this.bio = userData.getBio();
        this.image = userData.getImage();
        this.token = token;
    }
}
