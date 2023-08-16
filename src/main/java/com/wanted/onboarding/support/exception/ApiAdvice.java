package com.wanted.onboarding.support.exception;

import com.wanted.onboarding.member.exception.AuthenticationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiAdvice {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Object> authenticationException(AuthenticationException ex) {
        return ResponseEntity.status(200).body(ex.errorCode.defaultMessage());
    }
}
