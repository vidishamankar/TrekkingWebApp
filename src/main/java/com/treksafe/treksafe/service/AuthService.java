package com.treksafe.treksafe.service;
import com.treksafe.treksafe.model.RegisterRequest;
import com.treksafe.treksafe.model.User;
import com.treksafe.treksafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service layer containing the core business logic for user authentication.
 * Handles registration, login verification, and password encoding.
 */
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Registers a new user.
     * @param request DTO containing user registration details (name, email, password).
     * @throws IllegalStateException if a user with the given email already exists.
     */
    public int registerNewUser(RegisterRequest request) {
        // 1. Check if the user already exists
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalStateException("An account already exists with this email address: " + request.getEmail());
        }

        // 2. Hash the password for secure storage
        String encodedPassword = passwordEncoder.encode(request.getPassword());

        // 3. Create and save the new User entity
        User newUser = new User();
        newUser.setFullName(request.getFullName());
        newUser.setEmail(request.getEmail());
        newUser.setPasswordHash(encodedPassword);

        userRepository.save(newUser);
        return 0;
    }

    /**
     * Authenticates a user by checking the stored hashed password against the provided password.
     * @param email The user's email address.
     * @param password The raw password provided during login.
     * @return true if credentials are valid, false otherwise.
     */
    public boolean authenticateUser(String email, String password) {
        // 1. Find the user by email in the database
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            // User not found
            return false;
        }

        User user = userOptional.get();

        // 2. Use PasswordEncoder to check the raw password against the stored hash
        // This is secure and resistant to timing attacks.
        return passwordEncoder.matches(password, user.getPasswordHash());
    }
}
