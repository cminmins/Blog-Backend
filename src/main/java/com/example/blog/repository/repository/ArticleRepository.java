package com.example.blog.repository.repository;

import com.example.blog.domain.article.Article;
import com.example.blog.domain.article.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository {

    void createArticle(Article article);
    void updateArticle(String id, Article article);
    void deleteArticle(String slug);

    boolean isFollowingArticle(String userId, String articleId);
    int countArticleFavorites(String articleId);

    void saveTag(Tag tag);
    void saveArticleTag(String articleId, String tagId);

    Optional<List> findByQuery(String tag, String favorited, String author, int offset, int limit);
    Optional<Article> findBySlug(String slug);
    Optional<List<String>> findTagList(String articleId);
    Optional<String> findByTag(String name);
    Optional<Article> findById(String articleId);

    Optional<List> getUserFeed(String id, int limit, int offset);
}
