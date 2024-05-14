package com.example.firstdemo.Exception;

import lombok.Builder;
import lombok.Data;

@Data // Lombok註解
@Builder // Lombok註解
public class ErrorResponse {
    private int status;
    private String message;

}