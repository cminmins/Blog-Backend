package com.example.blog.repository.repository;

import com.example.blog.repository.mybatis.mapper.follow.FollowRelation;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FollowRepository {
    void follow(String currentUser, String followUser);

    void unfollow(String currentUser, String unfollowUser);

    boolean isFollowing(String currentUser, String followUser);
}
