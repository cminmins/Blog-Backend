package com.example.blog.api.article;

import com.example.blog.domain.article.Article;
import com.example.blog.domain.user.User;
import com.example.blog.service.requestDTO.RequestUserRegister;
import com.example.blog.service.responseDTO.ArticleData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ArticleDTOMapper {
    @Mapping(target = "favorited", constant = "false")
    @Mapping(target = "favoritesCount", constant = "0")
    @Mapping(target = "author", expression = "java(null)")
    ArticleData entityToDTO(Article article);
}
