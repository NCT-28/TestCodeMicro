package com.spring.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends ServletException {
    public ConflictException(String message) { super(message); }
}