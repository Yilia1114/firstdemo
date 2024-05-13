package com.example.firstdemo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.crypto.MacProvider;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

@Component
public class JwtUtils {
    private static final String SECRET_KEY = "EYugV1MnhQNTLq3f3uUO4zrZaNCafIYZiLquRWcTQh8aIDlC3Ar2uKdIzUDTmRBY"; // 請替換為一個安全的密鑰
    private static final long EXPIRATION_TIME = 864_000_000; // 有效期為10天（以毫秒為單位）

    public static String generateToken(String username) {
        return Jwts.builder()
                .claim("userId", username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }



}
