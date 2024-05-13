package com.example.firstdemo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;


@Component
public class JwtUtils {
    private static final String SECRET_KEY = "pGhUC03l8+n9xAuqM/0+PmOqHX58fO7r/ckZw1gzO4XxycOdqQYv73ni7QKgaRxet4RRmYZ2pR2//FjyeULLpQ=="; // 請替換為一個安全的密鑰
    private static final long EXPIRATION_TIME = 864_000_000; // 有效期為10天（以毫秒為單位）

    public static String generateToken(String username) {

        return Jwts.builder()
                .claim("userId", username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }


}
