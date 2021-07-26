package com.example.blog.repository.mybatis.mapper.follow;

import lombok.Data;

@Data
public class FollowRelation {
    String current_id;
    String follow_id;

    public FollowRelation(String current_id, String follow_id) {
        this.current_id = current_id;
        this.follow_id = follow_id;
    }
}
