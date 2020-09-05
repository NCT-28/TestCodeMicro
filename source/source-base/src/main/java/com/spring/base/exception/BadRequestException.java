package com.spring.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends ServletException {
    public BadRequestException(String message) {
        super(message);
    }
}