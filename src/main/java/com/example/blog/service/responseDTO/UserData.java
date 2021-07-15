package com.example.blog.service.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserData {
//    private String id;
    private String username = "";
    private String email =  "";
    private String bio = "";
    private String image = "";
}
