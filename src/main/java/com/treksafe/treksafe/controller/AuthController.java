package com.treksafe.treksafe.controller;

import com.treksafe.treksafe.model.LoginRequest;
import com.treksafe.treksafe.dto.RegisterRequest;
import com.treksafe.treksafe.model.User;
import com.treksafe.treksafe.service.AuthService;
import com.treksafe.treksafe.service.JwtService;
import com.treksafe.treksafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService; // To generate tokens
    private final UserService userService; // To find the user data

    @GetMapping("/status")
    public ResponseEntity<String> getStatus() {
        return ResponseEntity.ok("TrekSafe API is running.");
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        try {
            authService.registerNewUser(request);
            return ResponseEntity.ok("Registration successful. Please log in.");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Verify credentials
        if (authService.authenticateUser(request.getEmail(), request.getPassword())) {

            // 2. Fetch the User details to put inside the token
            Optional<User> userOpt = userService.findByEmail(request.getEmail());

            if (userOpt.isPresent()) {
                User user = userOpt.get();

                // 3. Generate the Real JWT Token
                String jwtToken = jwtService.generateToken(user);

                // 4. Return it as JSON so the frontend can read it
                return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "Login successful",
                        "token", jwtToken  // <--- This is what auth.js needs!
                ));
            }
        }

        return ResponseEntity.badRequest().body("Invalid credentials.");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody LoginRequest request) {
        System.out.println("Simulating password reset for: " + request.getEmail());
        return ResponseEntity.ok("If an account is associated with that email, a reset link has been sent.");
    }
}