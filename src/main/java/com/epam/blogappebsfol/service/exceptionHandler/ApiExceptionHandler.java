package com.epam.blogappebsfol.service.exceptionHandler;

import com.epam.blogappebsfol.domain.exception.PostDuplicateException;
import com.epam.blogappebsfol.domain.exception.PostNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {

    @ExceptionHandler(PostDuplicateException.class)
    public ResponseEntity<ErrorResponse> handlePostDuplicateException(Exception ex) {
        return new ResponseEntity<>(getErrorResponse("CREATING FAILED", ex), BAD_REQUEST);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePostNotFoundException(Exception ex) {
        return new ResponseEntity<>(getErrorResponse("UPDATING FAILED", ex), NOT_FOUND);
    }

    private ErrorResponse getErrorResponse(String message, Throwable ex) {
        List<String> details = new ArrayList<>();
        details.add(ex.getLocalizedMessage());

        log.error(String.format("Error: %s, cause: %s", message, Arrays.toString(details.toArray())));

        return new ErrorResponse(message, details);
    }
}
