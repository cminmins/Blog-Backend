package com.example.blog.api.exception;

import java.util.function.Supplier;

public class InvalidAuthenticationException extends RuntimeException {
    public InvalidAuthenticationException() {
        super("invalid email or password");
    }
}
