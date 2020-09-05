package com.spring.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ForbiddenException extends ServletException {
    public ForbiddenException(String message) {
        super(message);
    }
}