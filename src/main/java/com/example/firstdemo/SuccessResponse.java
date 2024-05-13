package com.example.firstdemo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL) // 只包含非 null 的屬性
public class SuccessResponse {
    private int status;
    private String message;
    private Object data; // 將 data 作為一個 Object 屬性
    private String token;

    public static SuccessResponse successMessage(String message) {
        return SuccessResponse.builder()
                .status(1)
                .message(message)
                .build();
    }

    public static SuccessResponse successToken(String token) {
        return SuccessResponse.builder()
                .status(1)
                .token(token)
                .build();
    }

    public static SuccessResponse successWithData(String message, Object data) {
        return SuccessResponse.builder()
                .status(1)
                .message(message)
                .data(data)
                .build();
    }

}
