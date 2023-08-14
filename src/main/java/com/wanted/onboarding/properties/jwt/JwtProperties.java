package com.wanted.onboarding.properties.jwt;


import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;

@ConfigurationProperties(prefix = "app.jwt")
@ConfigurationPropertiesBinding
public record JwtProperties(
        //1. 토큰 Properties에서 jwt 비밀번호를 가져온다.
        String secret,
        Long expiredIn
){
    public Key getSecretKey() {
        byte[] keyBytes = DatatypeConverter.parseBase64Binary(secret);

        return new SecretKeySpec(keyBytes, SignatureAlgorithm.HS256.getJcaName());
    }
}