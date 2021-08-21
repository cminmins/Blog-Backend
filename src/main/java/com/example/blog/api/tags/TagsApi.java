package com.example.blog.api.tags;

import com.example.blog.repository.repository.TagsRepository;
import com.example.blog.service.responseDTO.ArticleData;
import com.example.blog.service.tags.TagsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tags")
public class TagsApi {
    private TagsService tagsService;

    public TagsApi(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    private Map<String, Object> TagsList(List<String> tags) {
        return new HashMap<String, Object>() {
            {
                put("tags", tags);
            }
        };
    }

    @GetMapping
    public ResponseEntity getTags() {
        List<String> tagsList = tagsService.findAllTags();
        return ResponseEntity.ok().body(TagsList(tagsList));
    }
}
