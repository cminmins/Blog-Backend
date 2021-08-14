package com.example.blog.api.article;

import com.example.blog.domain.user.User;
import com.example.blog.service.articles.ArticleService;
import com.example.blog.service.profile.FollowUserService;
import com.example.blog.service.requestDTO.RequestCreateArticles;
import com.example.blog.service.requestDTO.RequestUpdateArticle;
import com.example.blog.service.responseDTO.ArticleData;
import com.example.blog.service.responseDTO.ProfileData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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


    @PostMapping
    public ResponseEntity createArticle(@AuthenticationPrincipal User user,
                                        @Valid @RequestBody RequestCreateArticles requestCreateArticles) {
        ArticleData articleData = articleService.createArticle(user, requestCreateArticles);
        return ResponseEntity.ok().body(SingleArticleResponse(articleData));
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
}
