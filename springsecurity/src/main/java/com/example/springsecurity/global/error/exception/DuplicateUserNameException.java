package com.example.springsecurity.global.error.exception;

import com.example.springsecurity.global.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DuplicateUserNameException extends RuntimeException{
    final ErrorCode errorCode;
}
