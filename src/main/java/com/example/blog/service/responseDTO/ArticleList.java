package com.example.blog.service.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticleList {
    @JsonProperty("articles")
    private List<ArticleData> articleData;

    @JsonProperty("articlesCount")
    private int count;

    public ArticleList(List<ArticleData> articleData, int count) {
        this.articleData = articleData;
        this.count = count;
    }
}
