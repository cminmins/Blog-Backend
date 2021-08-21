package com.example.blog.service.articles;

import com.example.blog.api.article.ArticleDTOMapper;
import com.example.blog.domain.article.Article;
import com.example.blog.domain.article.Article_favorited;
import com.example.blog.domain.article.Author;
import com.example.blog.domain.article.Tag;
import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.ArticleRepository;
import com.example.blog.repository.repository.FollowRepository;
import com.example.blog.service.profile.FollowUserService;
import com.example.blog.service.requestDTO.RequestCreateArticles;
import com.example.blog.service.requestDTO.RequestUpdateArticle;
import com.example.blog.service.responseDTO.ArticleData;
import com.example.blog.service.responseDTO.ArticleList;
import com.example.blog.service.responseDTO.ProfileData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private ArticleDTOMapper articleDTOMapper;
    private FollowUserService followUserService;

    public ArticleService(ArticleRepository articleRepository, ArticleDTOMapper articleDTOMapper, FollowUserService followUserService) {
        this.articleRepository = articleRepository;
        this.articleDTOMapper = articleDTOMapper;
        this.followUserService = followUserService;
    }

    public ArticleData createArticle(User user, RequestCreateArticles requestCreateArticles) {
        Article article = new Article(user.getId(),
                requestCreateArticles.getTitle(),
                requestCreateArticles.getDescription(),
                requestCreateArticles.getBody(),
                requestCreateArticles.getTagList());
        articleRepository.createArticle(article);
        for (String tagName : article.getTagList()) {
            String tagId = articleRepository.findByTag(tagName)
                    .orElseGet(() -> {
                        articleRepository.saveTag(new Tag(tagName));
                        return articleRepository.findByTag(tagName).get();
                    });
            articleRepository.saveArticleTag(article.getId(), tagId); }
        ArticleData articleData = articleDTOMapper.entityToDTO(article);
        articleData.setAuthor(followUserService.getProfileById(article.getAuthorId(), article.getAuthorId()));
        return articleData;
    }

    public ArticleData getArticle(User user, String slug) {
        Article article = articleRepository.findBySlug(slug).orElse(null);
        if (article == null) {
            return null;
        }
        ArticleData articleData = articleDTOMapper.entityToDTO(article);
        articleData.setAuthor(followUserService.getProfileById(article.getAuthorId(), article.getAuthorId()));
        articleRepository.findTagList(article.getId())
                .ifPresent(tagList -> articleData.setTagList(tagList));
        articleData.setFavorited(articleRepository.isFollowingArticle(user.getId(), article.getAuthorId()));
        articleData.setFavoritesCount(articleRepository.countArticleFavorites(article.getId()));
        return articleData;
    }

    public ArticleData updateArticle(String slug, RequestUpdateArticle requestUpdateArticle) {
        Article article = articleRepository.findBySlug(slug).orElse(null);
        if (article == null) {
            return null;
        }
        article.update(requestUpdateArticle.getTitle(), requestUpdateArticle.getDescription(), requestUpdateArticle.getBody());
        articleRepository.updateArticle(article.getId(), article);
        return articleDTOMapper.entityToDTO(article);
    }

    public void deleteArticle(String slug) {
        Article article = articleRepository.findBySlug(slug).orElse(null);
        if (article == null) {
            return;
        }
        articleRepository.deleteArticle(slug);
        return;
    }

    public ArticleList listArticle(String tag, String favorited, String author, int offset, int limit, User user) {
        List<String> articleList = articleRepository.findByQuery(tag, favorited, author, offset, limit).orElse(null);
        if (articleList == null) {
            return null;
        }
        List<ArticleData> articleDataList = new ArrayList<>();
        for (String articleId : articleList) {
            articleRepository.findById(articleId).ifPresent(
                    article -> {
                        ArticleData articleData = articleDTOMapper.entityToDTO(article);
                        articleData.setAuthor(followUserService.getProfileById(article.getAuthorId(), article.getAuthorId()));
                        articleRepository.findTagList(article.getId()).ifPresent(tagList -> articleData.setTagList(tagList));
                        articleData.setFavorited(articleRepository.isFollowingArticle(user.getId(), article.getAuthorId()));
                        articleData.setFavoritesCount(articleRepository.countArticleFavorites(article.getId()));
                        articleDataList.add(articleData);
                    });
        }
            return new ArticleList(articleDataList, articleDataList.size());
    }

    public ArticleList getFeedArticles(User user, int limit, int offset) {
        List<String> follows_id = articleRepository.getUserFeed(user.getId(), limit, offset).orElse(null);
        if (follows_id == null) {
            return null;
        }
        List<ArticleData> articleDataList = new ArrayList<>();
        for (String id : follows_id) {
            articleRepository.findById(id).ifPresent(
                    article -> {
                        ArticleData articleData = articleDTOMapper.entityToDTO(article);
                        articleData.setAuthor(followUserService.getProfileById(article.getAuthorId(), article.getAuthorId()));
                        articleRepository.findTagList(article.getId()).ifPresent(tagList -> articleData.setTagList(tagList));
                        articleData.setFavorited(articleRepository.isFollowingArticle(user.getId(), article.getAuthorId()));
                        articleData.setFavoritesCount(articleRepository.countArticleFavorites(article.getId()));
                        articleDataList.add(articleData);
                    });
        }
        return new ArticleList(articleDataList, articleDataList.size());
    }
}
