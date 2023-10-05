package com.example.travel.handler;

import com.example.travel.dto.common.FailResponse;
import com.example.travel.exception.CustomException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public FailResponse customExceptionHandler(CustomException e, HttpServletResponse response){
        response.setStatus(e.getHttpStatus().value());
        log.debug("Error response - Status Code: {}, Headers: {}", response.getStatus(), response.getHeaderNames() );

        return FailResponse.fail(e.getMessage(), "Error");
    }

}
