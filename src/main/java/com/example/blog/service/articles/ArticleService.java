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
import com.example.blog.service.responseDTO.ArticleData;
import com.example.blog.service.responseDTO.ProfileData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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

    public Map<String, Object> getAuthor(User user, String targetId) {
        ProfileData profileData = followUserService.getProfileById(user.getId(), targetId);
        return new HashMap<String, Object>() {
            {
                put("author", profileData);
            }
        };
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
        articleData.setAuthor(getAuthor(user, user.getId()));
        return articleData;
    }

    public ArticleData getArticle(User user, String slug) {
        Article article = articleRepository.findBySlug(slug).orElse(null);
        if (article == null) {
            return null;
        }
        ArticleData articleData = articleDTOMapper.entityToDTO(article);
        articleData.setAuthor(getAuthor(user, article.getAuthorId()));
        articleRepository.findTagList(article.getId())
                .ifPresent(tagList -> articleData.setTagList(tagList));
        articleData.setFavorited(articleRepository.isFollowingArticle(user.getId(), article.getAuthorId()));
        articleData.setFavoritesCount(articleRepository.countArticleFavorites(article.getId()));
        return articleData;
    }
}
