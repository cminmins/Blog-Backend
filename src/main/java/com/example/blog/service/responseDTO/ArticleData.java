package com.example.blog.service.responseDTO;

import lombok.Data;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

@Data
@Setter
public class ArticleData {
    String slug;
    String title;
    String description;
    String body;
    ArrayList<String> tagList;
    String createdAt;
    String updatedAt;

    Map<String, Object> author;

    boolean favorited;
    int favoritesCount;
}
