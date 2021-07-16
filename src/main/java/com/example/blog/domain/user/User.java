package com.example.blog.domain.user;

import com.example.blog.util.Util;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User {
    private String id;
    private String email;
    private String username;
    private String password;
    private String bio;
    private String image;

    @Autowired
    private ModelMapper modelMapper;

    public User(String email, String username, String password, String bio, String image) {
//        this.id = id;
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.image = image;
    }

    public void update(String email, String username, String password, String bio, String image) {
        if (Util.isEmpty(email)) {
            this.email = email;
        }
        if (Util.isEmpty(username)) {
            this.email = username;
        }
        if (Util.isEmpty(password)) {
            this.email = password;
        }
        if (Util.isEmpty(bio)) {
            this.email = bio;
        }
        if (Util.isEmpty(image)) {
            this.email = image;
        }
    }

    public boolean isNotMatchPassword(String password) {
        return !this.password.equals(password);
    }
}
