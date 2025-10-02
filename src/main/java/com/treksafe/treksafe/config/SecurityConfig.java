package com.treksafe.treksafe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


/**
 * Configuration class for Spring Security.
 * Sets up which endpoints are public and which require authentication.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Fix for "No qualifying bean of type 'PasswordEncoder' found"
     * Defines the PasswordEncoder (using BCrypt) as a bean directly in the SecurityConfig
     * This is required by Spring Security and our AuthService.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use the industry-standard BCrypt hashing algorithm
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines the security rules for HTTP requests.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // We use the passwordEncoder() method directly now.
        PasswordEncoder encoder = passwordEncoder();

        http
                // Disable CSRF protection for API calls from a non-server-rendered frontend
                .csrf(csrf -> csrf.disable())

                // Configure request authorization
                .authorizeHttpRequests(auth -> auth
                        // Allow public access to all authentication endpoints
                        .requestMatchers("/api/v1/status", "/api/v1/login", "/api/v1/register", "/api/v1/forgot-password").permitAll()
                        // Require authentication for all other API endpoints
                        .anyRequest().authenticated()
                )
                // Fix for 'httpBasic() is deprecated'
                // Use basic HTTP authentication for protected routes (good for testing)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    /**
     * Provides an in-memory user detail manager for initial testing of protected routes.
     * This uses the same passwordEncoder() bean defined above.
     */
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        // Use the PasswordEncoder bean directly
        PasswordEncoder encoder = passwordEncoder();

        UserDetails user = User.builder()
                .username("testuser")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
