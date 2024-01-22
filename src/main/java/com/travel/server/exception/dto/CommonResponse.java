package com.travel.server.exception.dto;

import com.travel.server.exception.model.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class CommonResponse<T> {

    private String code;
    private String message;
    private T data;

    public static <T> CommonResponse<T> success(String code, T data, String message) {
        return CommonResponse.<T>builder()
                .code(code)
                .data(data)
                .message(message)
                .build();
    }

    public static <T> CommonResponse<T> fail(ErrorCode errorCode) {
        return CommonResponse.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .build();
    }

    public static <T> CommonResponse<T> fail(String message) {
        return CommonResponse.<T>builder()
                .code("E000")
                .message(message)
                .build();
    }

    public static <T> CommonResponse<T> success(T data) {
        return success("0000", data, null);
    }

    public static <T> CommonResponse<T> success(T data, String message) {
        return success("0000", data, message);
    }

    public static <T> CommonResponse<T> success() {
        return success(null);
    }

}