package com.example.firstdemo.controller.pojo;

import lombok.Data;
import java.time.LocalDateTime;

@Data //Lombok註解
public class ErrorDto {
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;}

