package com.example.blog.repository.mybatis.mapper.article;

import com.example.blog.domain.article.Article;
import com.example.blog.domain.article.Article_favorited;
import com.example.blog.domain.article.Author;
import com.example.blog.domain.article.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ArticleMapper {
    void createArticle(@Param("article") Article article);

    String findByTag(@Param("name") String name);

    void saveTag(@Param("tag") Tag tag);

    void saveArticleTag(@Param("articleId") String articleId, @Param("tagId") String tagId);

    Article findBySlug(@Param("slug") String slug);

    boolean isFollowingArticle(@Param("userId") String userId, @Param("articleId") String articleId);

    int countArticleFavorites(@Param("articleId") String articleId);

    List<String> findTagList(@Param("articleId") String articleId);
}
