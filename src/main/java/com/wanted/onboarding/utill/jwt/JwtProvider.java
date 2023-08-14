package com.wanted.onboarding.utill.jwt;

import com.wanted.onboarding.properties.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

import io.jsonwebtoken.io.Decoders;

@Component
public final class JwtProvider {

    private final Key secretKey;
    private final Long expiredIn;


    public JwtProvider(JwtProperties jwtProperties) {
        byte[] keyBytes = Decoders.BASE64.decode(jwtProperties.secret());
        secretKey = Keys.hmacShaKeyFor(keyBytes);
        expiredIn = jwtProperties.expiredIn();
    }

    public String generateAsUser(String email, String name) {
        return generate(email, name, "USER");
    }

    private String generate(String email, String name, String... roles) {
        Claims claims = Jwts.claims().setSubject(email);

        Date now = new Date();
        Date expireAt = new Date(now.getTime() + expiredIn);

        claims.put("name", name);
        claims.put("roles", roles);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expireAt)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }
}
