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
    private String id;
    private String slug;
    private String title;
    private String description;
    private String body;
    private List<String> tagList;
    private String createdAt;
    private String updatedAt;

    @JsonProperty("author")
    ProfileData author;

    boolean favorited;
    int favoritesCount;
}
