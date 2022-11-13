package com.amazingbooks.services.customers.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class RoleIssueException extends RuntimeException {
    public RoleIssueException(String msg) {
        super(msg);
    }
}
