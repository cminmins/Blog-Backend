package com.example.blog.api.profile;

import com.example.blog.domain.user.User;
import com.example.blog.service.responseDTO.ProfileData;
import com.example.blog.service.responseDTO.UserData;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface FollowDTOMapper {
    ProfileData entityToProfileData(User user);
}
