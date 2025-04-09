package com.example.sun_hopital_back_end.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
public class JwtUtils {

    @Value("QUcjdvn1oZK4h4feKPgAAedV0XZrwZY1P7dKS5JV0R8=")
    private String secret;
    public String generateToken(String data) {
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        return Jwts.builder()
                .setSubject(data)
                .signWith(key)
                .compact();
    }
}
