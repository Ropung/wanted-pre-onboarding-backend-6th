package com.wanted.onboarding;

import com.wanted.onboarding.properties.jwt.JwtProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@SpringBootApplication
@EnableConfigurationProperties(JwtProperties.class)
public class OnboardingApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnboardingApplication.class, args);
	}
}
