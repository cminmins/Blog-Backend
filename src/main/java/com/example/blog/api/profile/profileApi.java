package com.example.blog.api.profile;

import com.example.blog.repository.repository.UserRepository;
import com.example.blog.service.profile.followUserService;
import com.example.blog.service.responseDTO.ProfileData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/profiles/{username}")
public class profileApi {
    private followUserService followUserService;

    public profileApi(com.example.blog.service.profile.followUserService followUserService) {
        this.followUserService = followUserService;
    }

    private ResponseEntity profileResponse(ProfileData profile) {
        return ResponseEntity.ok(new HashMap<String, Object>() {{
            put("profile", profile);
        }});
    }

    @GetMapping
    public ResponseEntity getProfile(@AuthenticationPrincipal String id,
                                     @PathVariable("username") String username) {
        ProfileData profileData = followUserService.getProfile(id, username);
        return ResponseEntity.ok().body(profileResponse(profileData));
    }

    @PostMapping("/follow")
    public ResponseEntity followUser(@AuthenticationPrincipal String id,
                                     @PathVariable("username") String username) {
        ProfileData profileData = followUserService.followUser(id, username);
        return ResponseEntity.status(201).body(profileResponse(profileData));
    }

    @DeleteMapping("/follow")
    public ResponseEntity unfollowUser(@AuthenticationPrincipal String id,
                                       @PathVariable("username") String username) {
        ProfileData profileData = followUserService.unfollowUser(id, username);
        return ResponseEntity.ok().body(profileResponse(profileData));
    }
}
