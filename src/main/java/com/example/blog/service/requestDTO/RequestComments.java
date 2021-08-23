package com.example.blog.service.requestDTO;

import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@JsonRootName("comment")
public class RequestComments {
    @NotBlank(message = "can't be empty")
    private String body;
}
