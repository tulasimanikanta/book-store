package com.amazingbooks.services.books.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.GONE)
public class BookUnavailable extends RuntimeException {
    public BookUnavailable(String msg) {
        super(msg);
    }
}
