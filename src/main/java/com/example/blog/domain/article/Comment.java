package com.example.blog.domain.article;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class Comment {
    private String id;
    private String createdAt;
    private String updatedAt;
    private String body;
    private String user_id;

    public Comment(String user_id, String body) {
        this.id = UUID.randomUUID().toString();
        this.body = body;
        this.user_id = user_id;
        this.createdAt = makeTimeFromNow();
        this.updatedAt = createdAt;
    }

    public String makeTimeFromNow() {
        return LocalDateTime.now().toString();
    }
}
