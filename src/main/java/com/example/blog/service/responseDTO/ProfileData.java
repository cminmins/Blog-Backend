package com.example.blog.service.responseDTO;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class ProfileData {
    private String username;
    private String bio;
    private String image;
    private boolean following;
}
