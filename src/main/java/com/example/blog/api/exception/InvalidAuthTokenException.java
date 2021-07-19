package com.example.blog.api.exception;

public class InvalidAuthTokenException extends RuntimeException{
    public InvalidAuthTokenException() {
        super("invaild auth token");
    }
}
