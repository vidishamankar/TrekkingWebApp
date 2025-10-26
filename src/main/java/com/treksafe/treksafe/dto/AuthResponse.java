package com.treksafe.treksafe.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor; // Added for convenience

/**
 * Data Transfer Object (DTO) for structuring the API response after authentication or registration.
 * Standardizes the format for success/error messages returned to the client-side JavaScript.
 *
 * This structure corresponds to the JSON response expected by login.html and signup.html:
 * {
 * "status": "success" or "error",
 * "message": "A human-readable description",
 * "userId": 123
 * }
 */
@Data
@Builder
@NoArgsConstructor // Required for JSON deserialization by some frameworks
public class AuthResponse {
    private String status; // e.g., "success" or "error"
    private String message;
    private Long userId; // Optional: include user ID on success for client-side storage

    // Custom constructor required when using @Builder and adding @NoArgsConstructor
    public AuthResponse(String status, String message, Long userId) {
        this.status = status;
        this.message = message;
        this.userId = userId;
    }
}

