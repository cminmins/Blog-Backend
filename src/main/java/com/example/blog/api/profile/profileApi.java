package com.example.blog.api.profile;

import com.example.blog.domain.user.User;
import com.example.blog.service.profile.FollowUserService;
import com.example.blog.service.responseDTO.ProfileData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/profiles/{username}")
public class profileApi {
    private FollowUserService followUserService;

    public profileApi(com.example.blog.service.profile.FollowUserService followUserService) {
        this.followUserService = followUserService;
    }

    private ResponseEntity profileResponse(ProfileData profile) {
        return ResponseEntity.ok(new HashMap<String, Object>() {{
            put("profile", profile);
        }});
    }

    @GetMapping
    public ResponseEntity getProfile(@AuthenticationPrincipal User user,
                                     @PathVariable("username") String username) {
        ProfileData profileData = followUserService.getProfile(user.getId(), username);
        return ResponseEntity.ok().body(profileResponse(profileData));
    }

    @PostMapping("/follow")
    public ResponseEntity followUser(@AuthenticationPrincipal User user,
                                     @PathVariable("username") String username) {
        ProfileData profileData = followUserService.followUser(user.getId(), username);
        return ResponseEntity.status(201).body(profileResponse(profileData));
    }

    @DeleteMapping("/follow")
    public ResponseEntity unfollowUser(@AuthenticationPrincipal User user,
                                       @PathVariable("username") String username) {
        ProfileData profileData = followUserService.unfollowUser(user.getId(), username);
        return ResponseEntity.ok().body(profileResponse(profileData));
    }
}
