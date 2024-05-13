package com.example.firstdemo;

import lombok.Builder;
import lombok.Data;

@Data // Lombok註解
@Builder // Lombok註解
public class AuthResponse {
    private String token;

    public AuthResponse(String token) {
        this.token = token;
    }
}


