package com.amazingbooks.services.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnknownBookException extends RuntimeException {
    public UnknownBookException(String msg) {
        super(msg);
    }
}
