package com.example.blog.repository.mybatis.mapper.article;

import com.example.blog.domain.article.Article;
import com.example.blog.domain.article.Article_favorited;
import com.example.blog.domain.article.Author;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

@Mapper
public interface ArticleMapper {
    void createArticle(@Param("article") Article article);

    Article findBySlug(@Param("slug") String slug);

    boolean isFollowingArticle(@Param("userId") String userId, @Param("articleId") String articleId);

    int countArticleFavorites(@Param("articleId") String articleId);

    ArrayList<String> findTagList(@Param("articleId") String articleId);
}
