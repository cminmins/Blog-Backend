package com.example.blog.repository.mybatis.mapper.article;

import com.example.blog.domain.article.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.parameters.P;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Mapper
public interface ArticleMapper {
    void createArticle(@Param("article") Article article);

    void updateArticle(@Param("id") String id, @Param("article") Article article);

    void deleteArticle(@Param("slug") String slug);

    void saveTag(@Param("tag") Tag tag);

    void saveArticleTag(@Param("articleId") String articleId, @Param("tagId") String tagId);

    boolean isFollowingArticle(@Param("userId") String userId, @Param("articleId") String articleId);

    int countArticleFavorites(@Param("articleId") String articleId);

    List<Article> findByQuery(
            @Param("tag") String tag,
            @Param("favorited") String favorited,
            @Param("author") String author,
            @Param("offset") int offset,
            @Param("limit") int limit);

    List<String> findTagList(@Param("articleId") String articleId);

    Article findBySlug(@Param("slug") String slug);

    String findByTag(@Param("name") String name);

    Article findById(@Param("articleId") String articleId);

    List<String> getUserFeed(@Param("userId") String id,
                             @Param("limit") int limit,
                             @Param("offset") int offset);

    void createComments(@Param("article_id") String article_id, @Param("comment") Comment comment);

    List<Comment> getComments(@Param("id") String id);

    void deleteComment(@Param("articleId") String articleId,
                       @Param("commentId") String id);
}
