package com.rijai.LocationApi.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.rijai.LocationApi.model.Role;

@Service
public class JwtTokenService {

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(String username, Role role) {
        Date expiration = new Date(System.currentTimeMillis() + 86400000); // a day
        return Jwts.builder()
                .setSubject(username)
                .claim("role", role) 
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }
}
