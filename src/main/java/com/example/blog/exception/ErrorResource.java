package com.example.blog.exception;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;

import java.util.List;

@Getter
@JsonRootName("errors")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(using = ErrorResourceSerializer.class)
public class ErrorResource {
    private List<FieldErrorResource> fieldErrorResourceList;

    public ErrorResource(List<FieldErrorResource> fieldErrorResourceList) {
        this.fieldErrorResourceList = fieldErrorResourceList;
    }
}
