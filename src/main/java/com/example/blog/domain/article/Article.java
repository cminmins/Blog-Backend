package com.example.blog.domain.article;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Article {
    private String authorId;
    private String id;
    private String slug;
    private String title;
    private String description;
    private String body;
    private String createdAt;
    private String updatedAt;

    ArrayList<String> tagList;

    public Article(String authorId, String title, String description, String body, ArrayList<String> tagList) {
        this.authorId = authorId;
        this.id = UUID.randomUUID().toString();
        this.slug = toSlug(title);
        this.title = title;
        this.description = description;
        this.body = body;
        this.createdAt = makeTimeFromNow();
        this.updatedAt = this.createdAt;
        this.tagList = tagList;
    }

    public void update(String title, String description, String body, ArrayList<String> tagList) {
        if (StringUtils.hasText(title)) {
            this.updatedAt = makeTimeFromNow();
            this.slug = toSlug(title);
            this.title = title;
        }
        if (StringUtils.hasText(description)) {
            this.updatedAt = makeTimeFromNow();
            this.description = description;
        }
        if (StringUtils.hasText(body)) {
            this.updatedAt = makeTimeFromNow();
            this.body = body;
        }
        if (!tagList.isEmpty()) {
            this.tagList = tagList;
        }
    }

    public static String toSlug(String title) {
        return title.toLowerCase(Locale.ROOT).replaceAll(" ", "-");
    }

    public String makeTimeFromNow() {
        return LocalDateTime.now().toString();
    }

}
