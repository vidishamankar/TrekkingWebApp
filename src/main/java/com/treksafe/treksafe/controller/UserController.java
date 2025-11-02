package com.treksafe.treksafe.controller;

import com.treksafe.treksafe.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    /**
     * A protected endpoint to get the currently authenticated user's details.
     * This is used by the frontend to check if a user is logged in.
     *
     * @param user Spring Security automatically injects the authenticated UserDetails principal.
     * @return A map of the user's details, or a 401 error if not authenticated.
     */
    @GetMapping("/me")
    public ResponseEntity<?> getUserInfo(@AuthenticationPrincipal User user) {

        // This handles both JWT (if you set it up) and OAuth2/Session users
        if (user == null) {
            // This should ideally not be hit if SecurityConfig is correct,
            // but it's a good fallback.
            return ResponseEntity.status(401).body(Map.of("error", "User not authenticated"));
        }

        // Return the user's details
        // We use a Map to send just the info the frontend needs
        return ResponseEntity.ok(Map.of(
                "email", user.getEmail(),
                "name", user.getName() // Assuming your User model has getFullName()
        ));
    }
}