package com.example.blog.service.requestDTO;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

@Data
@JsonRootName("article")
public class RequestUpdateArticle {
    private String title;
    private String description;
    private String body;
}
