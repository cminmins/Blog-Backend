package com.example.blog.domain.user;

import com.example.blog.util.Util;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class User {
    private String id;
    private String email;
    private String username;
    private String password;
    private String bio;
    private String image;

    public User(String email, String username, String password, String bio, String image) {
        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.username = username;
        this.password = password;
        this.bio = bio;
        this.image = image;
    }

    public void update(String email, String username, String password, String bio, String image) {
        if (!Util.isEmpty(email)) {
            this.email = email;
        }
        if (!Util.isEmpty(username)) {
            this.username = username;
        }
        if (!Util.isEmpty(password)) {
            this.password = password;
        }
        if (!Util.isEmpty(bio)) {
            this.bio = bio;
        }
        if (!Util.isEmpty(image)) {
            this.image = image;
        }
    }

    public boolean isNotMatchPassword(String password) {
        return !this.password.equals(password);
    }
}
