package com.spring.base.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.ServletException;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<?> generateError(HttpStatus status, Exception ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new ErrorDetails(new Date(), status.value(), status.getReasonPhrase(), ex.getMessage()), status);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<?> conflictException(ConflictException ex) {
        return this.generateError(HttpStatus.CONFLICT, ex);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequestException(BadRequestException ex) {
        return this.generateError(HttpStatus.BAD_REQUEST, ex);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<?> unauthorizedException(UnauthorizedException ex) {
        return this.generateError(HttpStatus.UNAUTHORIZED, ex);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFoundException(NotFoundException ex) {
        return this.generateError(HttpStatus.NOT_FOUND, ex);
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> forbiddenException(ForbiddenException ex) {
        return this.generateError(HttpStatus.FORBIDDEN, ex);
    }

    @ExceptionHandler(UnavailableForLegalReasonsException.class)
    public ResponseEntity<?> unavailableForLegalReasonsException(UnavailableForLegalReasonsException ex) {
        return this.generateError(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS, ex);
    }

    @ExceptionHandler({ExtendServletException.class})
    public ResponseEntity<?> extendServletExceptionHandler(ServletException ex) {
        return this.generateError(HttpStatus.SERVICE_UNAVAILABLE, ex);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> globalExceptionHandler(Exception ex) {
        return this.generateError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
    }
}