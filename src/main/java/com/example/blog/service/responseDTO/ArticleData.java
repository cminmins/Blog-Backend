package com.example.blog.service.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
@Setter
public class ArticleData {
    String slug;
    String title;
    String description;
    String body;
    List<String> tagList;
    String createdAt;
    String updatedAt;

    @JsonProperty("author")
    ProfileData author;

    boolean favorited;
    int favoritesCount;
}
