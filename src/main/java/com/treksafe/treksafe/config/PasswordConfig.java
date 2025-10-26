package com.treksafe.treksafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class to provide the global PasswordEncoder bean.
 * The BCryptPasswordEncoder is the standard, secure way to hash passwords in Spring Boot.
 *
 * Placed in: src/main/java/com/treksafe/treksafe/config/
 */
@Configuration
public class PasswordConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt is the standard, secure hashing algorithm for passwords.
        return new BCryptPasswordEncoder();
    }
}
