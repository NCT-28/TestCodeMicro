package com.spring.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.ServletException;

@ResponseStatus(value = HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
public class UnavailableForLegalReasonsException extends ServletException {
    public UnavailableForLegalReasonsException(String message) { super(message); }
}