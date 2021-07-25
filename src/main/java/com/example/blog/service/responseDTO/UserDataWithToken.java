package com.example.blog.service.responseDTO;

import com.example.blog.domain.user.User;
import lombok.Getter;

@Getter
public class UserDataWithToken {
    private String email;
    private String username;
    private String bio;
    private String image;
    private String token;

    public UserDataWithToken(UserData userData, String token) {
        this.email = userData.getEmail();
        this.token = token;
        this.username = userData.getUsername();
        this.bio = userData.getBio();
        this.image = userData.getImage();
    }
}
