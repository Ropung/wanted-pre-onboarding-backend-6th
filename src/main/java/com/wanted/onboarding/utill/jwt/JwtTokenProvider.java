package com.wanted.onboarding.utill.jwt;

import com.wanted.onboarding.properties.jwt.JwtProperties;
import com.wanted.onboarding.security.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtProperties jwtProperties;

    public String createToken(final Long id) {
        Date ext = new Date(); // 토큰 만료 시간
        ext.setTime(ext.getTime() + jwtProperties.expiredIn());

        return Jwts.builder()
                .setId(String.valueOf(id))
                .setSubject("Onboarding") // 토큰 용도
                .setExpiration(Date.from(Instant.now().plus(jwtProperties.expiredIn(), ChronoUnit.SECONDS))) // 토큰 만료 시간 설정
                .signWith(jwtProperties.getSecretKey(), SignatureAlgorithm.HS256) // HS256과 Key로 Sign
                .compact(); // 토큰 생성
    }

    public CustomUserDetails getCustomUserDetails(final String token) {
        Assert.hasText(token, "token parameter must not be empty or null");

        return new CustomUserDetails(getId(token));
    }

    public Long getId(final String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProperties.secret()))
                .build()
                .parseClaimsJws(token)
                .getBody();

        return Long.valueOf(claims.getId());
    }

    public Boolean validate(final String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(jwtProperties.secret()))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            return true;
        } catch (ExpiredJwtException expiredJwtException) {
            return false;
        } catch (JwtException jwtException) {
            return false;
        }
    }

    public String resolveToken(HttpServletRequest request) {
        final String token = request.getHeader("Authorization");

        if(token != null && token.startsWith("Bearer")) {
            return token.substring(7);
        }else {
            return null;
        }
    }
}
