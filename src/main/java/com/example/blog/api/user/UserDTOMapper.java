package com.example.blog.api.user;

import com.example.blog.domain.user.User;
import com.example.blog.service.requestDTO.RequestUserRegister;
import com.example.blog.service.responseDTO.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Value;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface UserDTOMapper{
    UserData entityToUserData(User user);

    @Mapping(target = "id", expression = "java(UUID.randomUUID().toString())")
    @Mapping(target = "image", constant = "")
    @Mapping(target = "bio", constant = "")
    User dtoToUser (RequestUserRegister requestUserRegister);
}
