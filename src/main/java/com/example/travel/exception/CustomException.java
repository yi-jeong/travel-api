package com.example.travel.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException{

    private HttpStatus httpStatus;
    private String errorCode;

    public CustomException(String errorCode, HttpStatus status, String message) {
        super(message);

        this.httpStatus = status;
        this.errorCode = errorCode;

    }

}
