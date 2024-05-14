package com.example.firstdemo.util;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Base64;

public class KeyGenerator {

    public static String generateSecureHS512Key() {
        // 創建安全的HS512金鑰
        byte[] keyBytes = Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded();

        // 將byte[]轉換為Base64字串
        String base64Key = Base64.getEncoder().encodeToString(keyBytes);

        return base64Key;
    }

    public static void main(String[] args) {
        // 呼叫生成金鑰的方法並輸出結果
        String secureKey = generateSecureHS512Key();
        System.out.println("Secure HS512 Key: " + secureKey);
    }
}

