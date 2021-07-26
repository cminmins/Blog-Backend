package com.example.blog.repository.mybatis.mapper.follow;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FollowMapper {
    void follow(@Param("currentUser") String currentUser,
                @Param("followUser") String followUser);

    void unfollow(@Param("currentUser") String currentUser,
                  @Param("unfollowUser") String unfollowUser);

    boolean isFollowing(@Param("curruentUser") String currentUser,
                       @Param("followUser") String followUser);
}
