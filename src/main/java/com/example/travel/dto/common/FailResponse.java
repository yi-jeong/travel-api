package com.example.travel.dto.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FailResponse<T> {

    private String errorCode;
    private String message;

    public static <T> FailResponse<T> fail(String message, String errorCode) {
        return FailResponse.<T>builder()
                .errorCode(errorCode)
                .message(message)
                .build();
    }

}