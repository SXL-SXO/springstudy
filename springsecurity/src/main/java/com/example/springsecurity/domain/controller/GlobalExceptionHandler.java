package com.example.springsecurity.domain.controller;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ExpiredJwtException.class)
    public Map<String, String> handleException(ExpiredJwtException e){
        Map<String, String> map = new HashMap<>();
        map.put("errMsg", e.getMessage());
        return map;
    }

    // exception 이 마지막이어야 한다
    @ExceptionHandler(Exception.class)
    public Map<String, String> handleException(Exception e){
        Map<String, String> map = new HashMap<>();
        map.put("errMsg", e.getMessage());
        return map;
    }

}
