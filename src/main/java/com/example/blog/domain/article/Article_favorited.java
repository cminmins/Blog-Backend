package com.example.blog.domain.article;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class Article_favorited {
    private boolean favorited;
    private int favoritesCount;

    public Article_favorited(boolean favorited, int favoritesCount) {
        this.favorited = favorited;
        this.favoritesCount = favoritesCount;
    }
}
