package com.example.blog.repository.repository;

import com.example.blog.domain.article.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface ArticleRepository {

    void createArticle(Article article);
    Optional<Article> findBySlug(String slug);

    boolean isFollowingArticle(String userId, String articleId);

    int countArticleFavorites(String articleId);

    Optional<ArrayList<String>> findTagList(String articleId);
}
