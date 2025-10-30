package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.User;
import com.treksafe.treksafe.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

/**
 * Custom implementation of Spring Security's UserDetailsService.
 * This class is responsible for loading user-specific data during authentication.
 */
@Service // Marks this class as a Spring Service component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructor injection for the UserRepository.
     * @param userRepository The JPA repository for accessing User data.
     */
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Core method used by Spring Security to retrieve a user's details by username.
     *
     * @param username The username (email) provided during login.
     * @return A UserDetails object containing the user's username, password, and authorities.
     * @throws UsernameNotFoundException if the user does not exist.
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 1. Fetch the user entity from the database using the repository.
        // NOTE: We use findByEmail because the 'username' parameter here is actually the user's email address.
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email (username): " + username));

        // 2. Map your application's User entity to Spring Security's UserDetails object.
        // We rely on the getUsername() and getPassword() methods added to the User model
        // to conform to the UserDetails contract.
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),       // Returns user.getEmail()
                user.getPassword(),       // Returns user.getPasswordHash()
                Collections.emptyList()   // Placeholder for authorities/roles
        );
    }
}

