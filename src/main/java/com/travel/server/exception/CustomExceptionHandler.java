package com.travel.server.exception;

import com.travel.server.exception.dto.CommonResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(CustomException.class)
    public CommonResponse customExceptionHandler(CustomException e, HttpServletResponse response){
        response.setStatus(e.getErrorCode().getStatus());

        return CommonResponse.fail(e.getErrorCode());
    }

}
