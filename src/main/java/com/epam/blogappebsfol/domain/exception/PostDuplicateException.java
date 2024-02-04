package com.epam.blogappebsfol.domain.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@ResponseStatus(value = BAD_REQUEST)
public class PostDuplicateException extends RuntimeException {
    public PostDuplicateException() {
        super("Post with such id already exists");
    }
}
