package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.User;
import com.treksafe.treksafe.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Requires Spring Security dependency in pom.xml
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service layer for all business operations related to User management.
 * This class handles registration logic, password hashing, and login verification.
 * Dependencies:
 * - UserRepository: For database operations (save, findByEmail).
 * - PasswordEncoder: For securely hashing and verifying passwords.
 * Placed in: src/main/java/com/treksafe/treksafe/service/
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // Injected from PasswordConfig

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user.
     * 1. Checks if a user with the provided email already exists.
     * 2. Hashes the plain-text password securely.
     * 3. Creates and saves the new User entity to the database.
     *
     * @param userDetails User object containing email, fullName, and plain password (temporarily in passwordHash field).
     * @return The newly saved User object with the ID.
     * @throws IllegalStateException if a user with the given email already exists.
     */
    public User registerNewUser(User userDetails) {
        // 1. Check if user already exists
        if (userRepository.findByEmail(userDetails.getEmail()).isPresent()) {
            throw new IllegalStateException("Email already registered: " + userDetails.getEmail());
        }

        // 2. Hash the password before saving
        // Note: userDetails.getPasswordHash() temporarily holds the plain password from the DTO.
        String hashedPassword = passwordEncoder.encode(userDetails.getPasswordHash());

        // 3. Create the final User entity with the hashed password
        User newUser = new User(
                userDetails.getEmail(),
                hashedPassword,
                userDetails.getFullName()
        );

        return userRepository.save(newUser);
    }

    /**
     * Attempts to authenticate a user during login.
     * 1. Finds the user by email.
     * 2. Compares the plain-text password with the stored hash using the PasswordEncoder.
     *
     * @param email The user's email.
     * @param plainPassword The password provided by the user.
     * @return An Optional containing the User if credentials are valid, otherwise empty.
     */
    public Optional<User> authenticate(String email, String plainPassword) {
        // 1. Find the user by email
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isEmpty()) {
            return Optional.empty(); // User not found
        }

        User user = userOptional.get();

        // 2. Check if the plain password matches the stored hash
        // IMPORTANT: Never compare the plain password directly!
        if (passwordEncoder.matches(plainPassword, user.getPasswordHash())) {
            return Optional.of(user); // Authentication successful
        } else {
            return Optional.empty(); // Password mismatch
        }
    }
}

