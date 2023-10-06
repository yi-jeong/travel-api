package com.example.travel.dto.common;

import com.example.travel.models.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class FailResponse<T> {

    private String errorCode;
    private String message;

    public static <T> FailResponse<T> fail(ErrorCode errorCode) {
        return FailResponse.<T>builder()
                .errorCode(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

}