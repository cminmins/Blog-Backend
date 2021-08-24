package com.example.blog.exception;

public class InvalidAuthenticationException extends RuntimeException {
    public InvalidAuthenticationException(){
        super("Invalid email or password");
    }
}
