package com.treksafe.treksafe.service;

import com.treksafe.treksafe.dto.RegisterRequest;
import com.treksafe.treksafe.model.Role;
import com.treksafe.treksafe.model.User;
import com.treksafe.treksafe.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class responsible for business logic related to User operations,
 * primarily registration and interaction with the UserRepository.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // Constructor Injection
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registers a new user with default role 'USER'.
     *
     * @param request The registration request DTO containing user details.
     * @return The newly created User entity.
     */
    public User register(RegisterRequest request) {
        // Build the User entity using the simplified fullName field
        var user = User.builder()
                // Directly use the name from the request as the full name
                .fullName(request.getFullName())
                .email(request.getEmail())
                // Encode the password before storing
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER) // Default role for new users
                .build();

        // Save the user to the database
        return userRepository.save(user);
    }

    // Add other user service methods here (e.g., findByEmail, updateUser, deleteUser)
}
