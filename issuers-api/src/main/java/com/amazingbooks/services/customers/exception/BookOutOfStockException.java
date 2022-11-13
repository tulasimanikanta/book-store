package com.amazingbooks.services.customers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BookOutOfStockException extends RuntimeException {
    public BookOutOfStockException(String msg) {
        super(msg);
    }
}
