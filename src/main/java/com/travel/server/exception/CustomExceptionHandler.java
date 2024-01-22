package com.travel.server.exception;

import com.travel.server.exception.dto.CommonResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CommonResponse<?> customExceptionHandler(CustomException e, HttpServletResponse response){
        response.setStatus(e.getErrorCode().getStatus());

        return CommonResponse.fail(e.getErrorCode());
    }

    @Override
    protected ResponseEntity<Object> createResponseEntity(
            @Nullable Object body,
            HttpHeaders headers,
            HttpStatusCode statusCode,
            WebRequest request) {

        body = CommonResponse.fail(body != null ? body.toString() : "none message.");
        return new ResponseEntity(body, headers, statusCode);
    }

}
