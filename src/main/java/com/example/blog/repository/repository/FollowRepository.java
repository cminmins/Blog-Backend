package com.example.blog.repository.repository;

import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository {
    void follow(String currentUser, String followUser);

    void unfollow(String currentUser, String unfollowUser);

    boolean isFollowing(String currentUser, String followUser);
}
