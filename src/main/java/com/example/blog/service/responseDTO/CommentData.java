package com.example.blog.service.responseDTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class CommentData {
    private String id;
    private String createdAt;
    private String updatedAt;
    private String body;

    @JsonProperty("author")
    private ProfileData profileData;
}
