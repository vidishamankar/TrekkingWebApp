package com.treksafe.treksafe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for user registration input.
 * It uses the 'fullName' and 'confirmPassword' fields as required by the client-side form.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String email;
    private String password;
    private String confirmPassword; // Added for comparison on the client/server side
    private String fullName;
}