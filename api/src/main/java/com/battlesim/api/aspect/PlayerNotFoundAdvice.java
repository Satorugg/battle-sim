package com.battlesim.api.aspect;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.battlesim.api.exception.PlayerNotFoundException;

@RestControllerAdvice
public class PlayerNotFoundAdvice {
    
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String playerNotFoundHandler(PlayerNotFoundException ex) {
        return ex.getMessage();
    }
}
