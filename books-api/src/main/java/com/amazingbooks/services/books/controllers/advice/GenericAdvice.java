package com.amazingbooks.services.books.controllers.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amazingbooks.services.books.exception.UnknownBookException;

@RestControllerAdvice
public class GenericAdvice {

    @ExceptionHandler(UnknownBookException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleException(UnknownBookException exception) {
        return String.format("Your request could not be processed due to \n %s\n", exception.getMessage());
    }
}
