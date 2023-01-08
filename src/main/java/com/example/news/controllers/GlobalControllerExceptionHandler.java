package com.example.news.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalControllerExceptionHandler.class);
    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<String> handle(HttpServletRequest request, Exception exception) {
        LOGGER.error(String.format("INTERNAL_SERVER_ERROR, %s, params: %s", request.getRequestURI(), request.getParameterMap()), exception);
        return ResponseEntity.internalServerError()
                .body(String.format("Некорректный запрос, попробуйте по другому! %s", exception.getMessage()));
    }
}
