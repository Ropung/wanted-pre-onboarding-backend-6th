package com.wanted.onboarding.config;

import com.wanted.onboarding.utill.jwt.JwtAuthenticationFilter;
import com.wanted.onboarding.utill.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
	private final JwtTokenProvider jwtTokenProvider;
	private final AuthenticationConfiguration authenticationConfiguration;

	public static final String[] GET_PUBLIC =
			new String[] {
					"/api/board/**",
			};

	public static final String[] POST_PUBLIC =
			new String[] {
					"/api/signup",
					"/api/login"
			};

	@Bean
	protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http.httpBasic()
				.disable()
				.csrf().disable()
				.sessionManagement(
						session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				)
				.authorizeRequests(auth ->
						auth
								.antMatchers(HttpMethod.GET, GET_PUBLIC).permitAll()
								.antMatchers(HttpMethod.POST, POST_PUBLIC).permitAll()
								.antMatchers("/api/admin/**").hasRole("ADMIN")
								.antMatchers("/api/user/**").hasRole("USER")
								.anyRequest().authenticated())

				.addFilterBefore(
						new JwtAuthenticationFilter(jwtTokenProvider, this.authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(12);
	}

	@Bean
	public AuthenticationManager authenticationManager()
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}
