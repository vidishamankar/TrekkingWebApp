package com.treksafe.treksafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

/**
 * Central configuration for Spring Security.
 * This file sets up the security filter chain, CORS rules, and URL authorization.
 * * NOTE: The PasswordEncoder is now defined in the separate PasswordConfig.java to avoid conflicts.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Defines the core security filter chain for the application.
     * 1. Enables CORS using the defined CorsConfigurationSource.
     * 2. Disables CSRF (necessary for stateless REST APIs).
     * 3. Sets session management to STATELESS (necessary for JWT tokens).
     * 4. Defines authorization rules (which URLs are public/protected).
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Enable CORS and apply configuration from the bean below
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // Disable Cross-Site Request Forgery (CSRF) protection
                // REST APIs typically rely on token-based authentication (JWT) rather than sessions/cookies.
                .csrf(AbstractHttpConfigurer::disable)

                // Configure session management to be stateless, as we use JWTs
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Define authorization rules for HTTP requests
                .authorizeHttpRequests(auth -> auth
                        // Allow public access to registration, login, and forgot password endpoints
                        // We use explicit paths here for better control, assuming the AuthController maps to /api/v1/auth
                        .requestMatchers("/api/v1/register", "/api/v1/login", "/api/v1/forgot-password").permitAll()

                        // Allow all endpoints under /api/v1/auth as public
                        .requestMatchers("/api/v1/auth/**").permitAll()

                        // Require authentication for all critical application endpoints
                        .requestMatchers("/api/v1/trails/**", "/api/v1/sos/**", "/api/v1/emergency/**").authenticated()

                        // Catch-all: all other requests must be authenticated
                        .anyRequest().authenticated()
                );

        // NOTE: Later, we will add a filter here for processing JWT tokens.

        return http.build();
    }

    /**
     * Defines the CORS configuration allowing requests from the frontend HTML files.
     * This is essential for the browser to allow our JavaScript to communicate with the backend API.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // IMPORTANT: In production, replace the wildcard with your specific frontend domain(s).
        configuration.setAllowedOrigins(Collections.singletonList("*"));

        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "X-Requested-With", "Accept"));

        // This is crucial for JWT authentication, allowing the Authorization header
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        // Apply this CORS configuration to all API paths
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
