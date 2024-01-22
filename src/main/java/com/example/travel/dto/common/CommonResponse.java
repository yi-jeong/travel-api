package com.example.travel.dto.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

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

    public static <T> CommonResponse<T> success(T data) {
        return success("0000", data, null);
    }

    public static <T> CommonResponse<T> success() {
        return success(null);
    }

}