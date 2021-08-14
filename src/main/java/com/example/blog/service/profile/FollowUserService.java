package com.example.blog.service.profile;

import com.example.blog.api.profile.FollowDTOMapper;
import com.example.blog.domain.user.User;
import com.example.blog.repository.repository.FollowRepository;
import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.responseDTO.ProfileData;
import org.springframework.stereotype.Service;

@Service
public class FollowUserService {
    private FollowRepository followRepository;
    private UserRepository userRepository;
    private FollowDTOMapper followDTOMapper;

    public FollowUserService(FollowRepository followRepository, UserRepository userRepository, FollowDTOMapper followDTOMapper) {
        this.followRepository = followRepository;
        this.userRepository = userRepository;
        this.followDTOMapper = followDTOMapper;
    }

    public ProfileData getProfile(String id, String username) {
        User followUser = userRepository.findByUsername(username).orElse(null);
        if (followUser == null) {
            return null;
        }
        ProfileData profileData = followDTOMapper.entityToProfileData(followUser);
        profileData.setFollowing(followRepository.isFollowing(id, followUser.getId()));
        return profileData;
    }

    public ProfileData getProfileById(String id, String targetId) {
        User followUser = userRepository.findById(targetId).orElse(null);
        if (followUser == null) {
            return null;
        }
        ProfileData profileData = followDTOMapper.entityToProfileData(followUser);
        profileData.setFollowing(followRepository.isFollowing(id, followUser.getId()));
        return profileData;
    }

    public ProfileData followUser(String id, String username) {
        User followUser = userRepository.findByUsername(username).orElse(null);
        if (followUser == null) {
            return null;
        }
        followRepository.follow(id, followUser.getId());
        return getProfile(id, username);
    }

    public ProfileData unfollowUser(String id, String username) {
        User followUser = userRepository.findByUsername(username).orElse(null);
        if (followUser == null) {
            return null;
        }
        followRepository.unfollow(id, followUser.getId());
        return getProfile(id, username);
    }
}
