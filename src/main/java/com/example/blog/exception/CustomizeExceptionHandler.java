package com.example.blog.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomizeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<Object> handleInvalidRequest(RuntimeException e, WebRequest request) {
        InvalidRequestException invalidRequestException = (InvalidRequestException) e;

        List<FieldErrorResource> errorResourceList = invalidRequestException.getErrors().getFieldErrors().stream()
                .map(fieldError -> new FieldErrorResource(
                        fieldError.getObjectName(),
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResource errorResource = new ErrorResource(errorResourceList);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(e, errorResource, httpHeaders, HttpStatus.UNPROCESSABLE_ENTITY, request);
    }

    @ExceptionHandler(InvalidAuthenticationException.class)
    public ResponseEntity<Object> handleInvalidAuthentication(InvalidAuthenticationException e, WebRequest webRequest) {
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new HashMap<String, Object>() {
                    {
                        put("message", e.getMessage());
                    }
                });
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<FieldErrorResource> fieldErrorResourceList = ex.getFieldErrors().stream()
                .map(fieldError -> new FieldErrorResource(
                        fieldError.getObjectName(),
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(new ErrorResource(fieldErrorResourceList));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorResource handleContrainViolation(ConstraintViolationException e, WebRequest webRequest) {
        List<FieldErrorResource> fieldErrorResourceList = new ArrayList<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            FieldErrorResource fieldErrorResource = new FieldErrorResource(
                    violation.getRootBeanClass().getName(),
                    getParam(violation.getPropertyPath().toString()),
                    violation.getConstraintDescriptor().getAnnotation().annotationType().getSimpleName(),
                    violation.getMessage());
            fieldErrorResourceList.add(fieldErrorResource);
        }
        return new ErrorResource(fieldErrorResourceList);
    }

    private String getParam(String s) {
        String[] splits = s.split("\\.");
        if (splits.length == 1) {
            return s;
        } else {
            return String.join(".", Arrays.copyOfRange(splits, 2, splits.length));
        }
    }
}
