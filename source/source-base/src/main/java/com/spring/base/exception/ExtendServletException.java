package com.spring.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;

@ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
public class ExtendServletException extends ServletException {
    public ExtendServletException(String message) {
        super(message);
    }
}