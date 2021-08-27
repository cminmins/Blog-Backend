package com.example.blog.service.articles;

import com.example.blog.api.article.ArticleDTOMapper;
import com.example.blog.api.article.CommentsDTOMapper;
import com.example.blog.domain.article.*;
import com.example.blog.domain.user.User;
import com.example.blog.exception.ResourceNotFoundException;
import com.example.blog.repository.repository.ArticleRepository;
import com.example.blog.service.profile.FollowUserService;
import com.example.blog.service.requestDTO.RequestCreateArticles;
import com.example.blog.service.requestDTO.RequestUpdateArticle;
import com.example.blog.service.responseDTO.ArticleData;
import com.example.blog.service.responseDTO.ArticleList;
import com.example.blog.service.responseDTO.CommentData;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    private ArticleRepository articleRepository;
    private ArticleDTOMapper articleDTOMapper;
    private CommentsDTOMapper commentsDTOMapper;
    private FollowUserService followUserService;

    public ArticleService(ArticleRepository articleRepository, ArticleDTOMapper articleDTOMapper, CommentsDTOMapper commentsDTOMapper, FollowUserService followUserService) {
        this.articleRepository = articleRepository;
        this.articleDTOMapper = articleDTOMapper;
        this.commentsDTOMapper = commentsDTOMapper;
        this.followUserService = followUserService;
    }

    public ArticleData makeArticleData(String articleId, User user) {
        return articleRepository.findById(articleId)
                .map(article -> {
                    ArticleData articleData = articleDTOMapper.entityToDTO(article);
                    articleRepository.findTagList(article.getId()).ifPresent(tagList -> articleData.setTagList(tagList));
                    articleData.setFavoritesCount(articleRepository.countArticleFavorites(articleData.getId()));
                    if (!user.isEmpty()) {
                        articleData.setAuthor(followUserService.getProfileById(article.getAuthorId(), user.getId()));
                        articleData.setFavorited(articleRepository.isFollowingArticle(user.getId(), article.getId()));
                    }
                    return articleData;
                }).orElse(null);
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
        articleData.setAuthor(followUserService.getProfileById(article.getAuthorId(), user.getId()));
        return articleData;
    }

    public ArticleData getArticle(User user, String slug) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
        return makeArticleData(article.getId(), user);
    }

    public ArticleData updateArticle(String slug, RequestUpdateArticle requestUpdateArticle) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
        article.update(requestUpdateArticle.getTitle(), requestUpdateArticle.getDescription(), requestUpdateArticle.getBody());
        articleRepository.updateArticle(article.getId(), article);
        return articleDTOMapper.entityToDTO(article);
    }

    public void deleteArticle(String slug) {
        articleRepository.findBySlug(slug).ifPresent(article -> articleRepository.deleteArticle(article.getSlug()));
        return;
    }

    public ArticleList listArticle(String tag, String favorited, String author, int offset, int limit, User user) {
        List<String> articleList = articleRepository.findByQuery(tag, favorited, author, offset, limit).orElseThrow(ResourceNotFoundException::new);
        List<ArticleData> articleDataList = articleList.stream()
                .map(articleId -> makeArticleData(articleId, user))
                .collect(Collectors.toList());
        return new ArticleList(articleDataList, articleDataList.size());
    }


    public ArticleList getFeedArticles(User user, int limit, int offset) {
        List<String> follows_id = articleRepository.getUserFeed(user.getId(), limit, offset).orElseThrow(ResourceNotFoundException::new);

        List<ArticleData> articleDataList = follows_id.stream()
                .map(articleId -> makeArticleData(articleId, user))
                .collect(Collectors.toList());
        return new ArticleList(articleDataList, articleDataList.size());
    }

    public CommentData createComments(User user, String slug, String body) {
        Comment comment = new Comment(user.getId(), body);
        Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);

        articleRepository.createComments(article.getId(), comment);
        CommentData commentData = commentsDTOMapper.entityToDTO(comment);
        commentData.setProfileData(followUserService.getProfileById(user.getId(), article.getAuthorId()));
        return commentData;
    }

    public List<CommentData> getComments(String slug) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
        List<CommentData> commentDataList = new ArrayList<>();
        List<Comment> commentList = articleRepository.findCommentsByArticleId(article.getId()).orElseThrow(ResourceNotFoundException::new);

        for(Comment comment : commentList){
            CommentData commentData = commentsDTOMapper.entityToDTO(comment);
            commentData.setProfileData(followUserService.getProfileById(comment.getUser_id(), article.getAuthorId()));
            commentDataList.add(commentData);
        }
        return commentDataList;
    }

    public void deleteComment(String slug, String id) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
        articleRepository.deleteComment(article.getId(), id);
        return;
    }

    public ArticleData favoriteArticle(User user, String slug) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
        articleRepository.favoriteArticle(user.getId(), article.getId());
        return makeArticleData(article.getId(), user);
    }

    public ArticleData unfavoriteArticle(User user, String slug) {
        Article article = articleRepository.findBySlug(slug).orElseThrow(ResourceNotFoundException::new);
        articleRepository.unfavoriteArticle(user.getId(), article.getId());
        return makeArticleData(article.getId(), user);
    }
}
