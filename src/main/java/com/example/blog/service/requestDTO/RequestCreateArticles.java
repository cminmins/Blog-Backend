package com.example.blog.service.requestDTO;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonRootName("article")
public class RequestCreateArticles {
    String title;
    String description;
    String body;
    ArrayList<String> tagList;
}
