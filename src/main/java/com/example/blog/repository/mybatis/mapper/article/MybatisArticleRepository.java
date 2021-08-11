package com.example.blog.repository.mybatis.mapper.article;

import com.example.blog.domain.article.Article;
import com.example.blog.domain.article.Tag;
import com.example.blog.repository.repository.ArticleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
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
    public Optional<List<String>> findTagList(String articleId) {
        return Optional.ofNullable(articleMapper.findTagList(articleId));
    }


    @Override
    public Optional<String> findByTag(String name) {
        return Optional.ofNullable(articleMapper.findByTag(name));
    }

    @Override
    public void saveTag(Tag tag) {
        articleMapper.saveTag(tag);
    }

    @Override
    public void saveArticleTag(String articleId, String tagId) {
        articleMapper.saveArticleTag(articleId, tagId);
    }
}