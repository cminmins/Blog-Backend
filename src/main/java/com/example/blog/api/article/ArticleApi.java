package com.example.blog.api.article;

import com.example.blog.domain.article.Article;
import com.example.blog.domain.user.User;
import com.example.blog.service.articles.ArticleService;
import com.example.blog.service.profile.FollowUserService;
import com.example.blog.service.requestDTO.RequestComments;
import com.example.blog.service.requestDTO.RequestCreateArticles;
import com.example.blog.service.requestDTO.RequestUpdateArticle;
import com.example.blog.service.responseDTO.ArticleData;
import com.example.blog.service.responseDTO.ArticleList;
import com.example.blog.service.responseDTO.CommentData;
import com.example.blog.service.responseDTO.ProfileData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/articles")
public class ArticleApi {
    private FollowUserService followUserService;
    private ArticleService articleService;

    public ArticleApi(FollowUserService followUserService, ArticleService articleService) {
        this.followUserService = followUserService;
        this.articleService = articleService;
    }

    private Map<String, Object> SingleArticleResponse(ArticleData articleData) {
        return new HashMap<String, Object>() {
            {
                put("article", articleData);
            }
        };
    }
    private Map<String, Object> SingleCommentsResponse(CommentData commentData) {
        return new HashMap<String, Object>() {
            {
                put("comment", commentData);
            }
        };
    }

    private Map<String, Object> MultiCommentsResponse(List<CommentData> commentDataList) {
        return new HashMap<String, Object>() {
            {
                put("comments", commentDataList);
            }
        };
    }

    @PostMapping
    public ResponseEntity createArticle(@AuthenticationPrincipal User user,
                                        @Valid @RequestBody RequestCreateArticles requestCreateArticles) {
        ArticleData articleData = articleService.createArticle(user, requestCreateArticles);
        return ResponseEntity.status(201).body(SingleArticleResponse(articleData));
    }

    @GetMapping
    public ResponseEntity listArticle(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "tag", required = false) String tag,
            @RequestParam(value = "favorited", required = false) String favorited,
            @RequestParam(value = "author", required = false) String author,
            @AuthenticationPrincipal User user) {
        ArticleList articleList = articleService.listArticle(tag, favorited, author, offset, limit, user);
        return ResponseEntity.ok().body(articleList);
    }

    @GetMapping("/{slug}")
    public ResponseEntity getArticle(@AuthenticationPrincipal User user,
                                     @PathVariable("slug") String slug) {
        ArticleData articleData = articleService.getArticle(user, slug);
        return ResponseEntity.ok().body(SingleArticleResponse(articleData));
    }

    @PutMapping("/{slug}")
    public ResponseEntity updateArticle(@PathVariable("slug") String slug,
                                        @Valid @RequestBody RequestUpdateArticle requestUpdateArticle) {
        ArticleData articleData = articleService.updateArticle(slug, requestUpdateArticle);
        return ResponseEntity.ok().body(SingleArticleResponse(articleData));
    }

    @DeleteMapping("/{slug}")
    public ResponseEntity deleteArticle(@PathVariable("slug") String slug) {
        articleService.deleteArticle(slug);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/feed")
    public ResponseEntity getFeedArticels(@AuthenticationPrincipal User user,
                                          @RequestParam(value = "limit", defaultValue = "20") int limit,
                                          @RequestParam(value = "offset", defaultValue = "0") int offset) {
        ArticleList articleList = articleService.getFeedArticles(user, limit, offset);
        return ResponseEntity.ok().body(articleList);
    }

    @PostMapping("/{slug}/comments")
    public ResponseEntity createComments(@AuthenticationPrincipal User user,
                                         @PathVariable("slug") String slug,
                                         @RequestBody RequestComments requestComments){
        CommentData commentData = articleService.createComments(user, slug, requestComments.getBody());
        return ResponseEntity.status(201).body(SingleCommentsResponse(commentData));
    }

    @GetMapping("/{slug}/comments")
    public ResponseEntity getComments(@PathVariable("slug") String slug){
        List<CommentData> commentDataList = articleService.getComments(slug);
        return ResponseEntity.ok().body(MultiCommentsResponse(commentDataList));
    }

    @DeleteMapping("/{slug}/comments/{id}")
    public ResponseEntity deleteComments(@PathVariable("slug") String slug,
                                         @PathVariable("id") String id) {
        articleService.deleteComment(slug, id);
        return ResponseEntity.noContent().build();
    }
}
