package com.sprk.security_demo_project.service;

import com.sprk.security_demo_project.dto.AuthRequest;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${my.secret}")
    private String secretKey;


    public String getToken(AuthRequest authRequest) {


        Map<String,Object> claims = new HashMap<>();

        return createToken(claims, authRequest.getUsername());
    }

    private String createToken(Map<String, Object> claims, String username) {
        return Jwts
                .builder()
                .header()
                .add(Map.of("alg","HS256","type","jwt"))
                .and()
                .claims(claims)
                .subject(username)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60)) // 1hour
                .signWith(getSignInKey())
                .compact();
    }

    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);

    }
}
