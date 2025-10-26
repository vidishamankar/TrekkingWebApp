package com.treksafe.treksafe.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for handling login and registration requests.
 * It carries the user credentials (email and password) from the client to the server.
 */
@Data
@NoArgsConstructor
public class AuthRequest {
    private String email;
    private String password;
    private String fullName; // Used specifically for registration
}
