package com.example.chatapp.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    // 예외 타입에 따른 처리 메서드 정의
    @ExceptionHandler(UserDoesNotExistException.class)
    public ResponseEntity<?> resourceNotFoundException(UserDoesNotExistException ex, WebRequest request) {
        // TODO
        log.info("resource Not Found");
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> globalExceptionHandler(Exception ex, WebRequest request) {
        log.info("global Exception occur");
        // TODO : exeception handle specify
        return ResponseEntity.internalServerError().build();
    }
}
