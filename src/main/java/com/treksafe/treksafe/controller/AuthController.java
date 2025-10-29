package com.treksafe.treksafe.controller;
import com.treksafe.treksafe.model.LoginRequest;
import com.treksafe.treksafe.dto.RegisterRequest;
import com.treksafe.treksafe.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling all public authentication-related API endpoints:
 * Login, Register, Forgot Password, and Application Status check.
 * This controller delegates business logic to the AuthService.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Endpoint to check application status. Allowed without authentication.
     * @return A status message.
     */
    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("TrekSafe API is running.");
    }

    /**
     * Handles user registration requests.
     * @param request DTO containing registration details (name, email, passwords).
     * @return Response indicating success or failure of registration.
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        // Passing the RegisterRequest DTO directly to the service.
        try {
            authService.registerNewUser(request);
            return ResponseEntity.ok("Registration successful. Please log in.");
        } catch (IllegalStateException e) {
            // This usually catches a duplicate email registration.
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Handles user login requests.
     * @param request DTO containing user's email and password.
     * @return Response indicating success or failure of login.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // The service layer authenticates the user against the hashed password in PostgreSQL.
        if (authService.authenticateUser(request.getEmail(), request.getPassword())) {
            // NOTE: In a production app, a JWT (JSON Web Token) would be generated and returned here.
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.badRequest().body("Invalid credentials.");
        }
    }

    /**
     * Handles forgotten password requests. This is a simulated endpoint
     * that confirms the request without actually sending an email.
     * @param request DTO containing the user's email.
     * @return A message confirming the reset request simulation.
     */
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody LoginRequest request) {
        // Simulate sending a password reset link to prevent email harvesting.
        System.out.println("Simulating password reset request for: " + request.getEmail());
        // For security, always return a non-committal success message, regardless of whether the email exists.
        return ResponseEntity.ok("If an account is associated with that email, a reset link has been sent.");
    }
}
