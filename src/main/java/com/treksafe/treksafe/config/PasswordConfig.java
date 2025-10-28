package com.treksafe.treksafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration class dedicated to providing a single PasswordEncoder bean.
 * This keeps the PasswordEncoder definition separate and clean, preventing conflicts
 * with the main SecurityConfig.
 */
@Configuration
public class PasswordConfig {

    /**
     * Defines the BCryptPasswordEncoder bean, which is used by the
     * UserService to hash user passwords before storage.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // BCrypt is the standard, secure way to handle password hashing.
        return new BCryptPasswordEncoder();
    }
}
