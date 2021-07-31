package com.example.blog.repository.mybatis.mapper.article;

import com.example.blog.domain.article.Article;
import com.example.blog.repository.repository.ArticleRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public class MybatisArticleRepository implements ArticleRepository {

    private ArticleMapper articleMapper;

    public MybatisArticleRepository(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public void createArticle(Article article) {
        articleMapper.createArticle(article);
    }

    @Override
    public Optional<Article> findBySlug(String slug) {
        return Optional.ofNullable(articleMapper.findBySlug(slug));
    }

    @Override
    public boolean isFollowingArticle(String userId, String articleId) {
        return articleMapper.isFollowingArticle(userId, articleId);
    }

    @Override
    public int countArticleFavorites(String articleId) {
        return articleMapper.countArticleFavorites(articleId);
    }

    @Override
    public Optional<ArrayList<String>> findTagList(String articleId) {
        return Optional.ofNullable(articleMapper.findTagList(articleId));
    }
}
