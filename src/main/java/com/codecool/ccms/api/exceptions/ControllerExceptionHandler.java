package com.codecool.ccms.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerExceptionHandler {

    private final String error404JSON = "{\n" +
            "  \"key\": \"NotFoundError\",\n" +
            "  \"msg\": \"Resource not found\"\n" +
            "}";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleConflict(MethodArgumentNotValidException ex) {
        return "dup";
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundHandler(NotFoundException ex) {
        return new ResponseEntity<>(error404JSON, HttpStatus.NOT_FOUND);
    }
}