package com.example.blog.api.article;

import com.example.blog.domain.article.Comment;
import com.example.blog.service.responseDTO.CommentData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentsDTOMapper {
    @Mapping(target = "profileData", expression = "java(null)")
    CommentData entityToDTO(Comment comment);
}
