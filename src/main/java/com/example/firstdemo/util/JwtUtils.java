package com.example.firstdemo.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;


@Component
public class JwtUtils {
    private static final String SECRET = "Yilia1114HappyUsedJwtToOpenDoors";
    private static final long EXPIRATION_TIME = 432_000_000;// 有效期為5天（以毫秒為單位）

    //產出
    public static String generateToken(String username) {
        Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());
        return Jwts.builder()
                .claim("username", username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    //驗證
    public boolean validateToken(String token) {
        try {
            Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //解析
    public String extractUsername(String token) {
        Key secretKey = Keys.hmacShaKeyFor(SECRET.getBytes());
        Claims claims = Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
        return claims.get("username", String.class);
    }



}
