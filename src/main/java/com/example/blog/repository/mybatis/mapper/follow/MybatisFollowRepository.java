package com.example.blog.repository.mybatis.mapper.follow;

import com.example.blog.repository.repository.FollowRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class MybatisFollowRepository implements FollowRepository {
    private FollowMapper followMapper;

    public MybatisFollowRepository(FollowMapper followMapper) {
        this.followMapper = followMapper;
    }

    @Override
    public void follow(String currentUser, String followUser) {
        followMapper.follow(currentUser, followUser);
    }

    @Override
    public void unfollow(String currentUser, String unfollowUser) {
        followMapper.unfollow(currentUser, unfollowUser);
    }

    @Override
    public boolean isFollowing(String currentUser, String followUser) {
        return followMapper.isFollowing(currentUser, followUser);
    }
}
