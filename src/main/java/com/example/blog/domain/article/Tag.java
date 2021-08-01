package com.example.blog.domain.article;

import lombok.Data;

import java.util.UUID;

@Data
public class Tag {
    private String id;
    private String name;

    public Tag(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }
}
