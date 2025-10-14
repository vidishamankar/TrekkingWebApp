package com.treksafe.treksafe.model;

/**
 * Data Transfer Object (DTO) to hold the credentials sent from the login form.
 * The field names (email, password) must match the JSON keys sent by the frontend.
 */
public class LoginRequest {
    private String email;
    private String password;

    // Standard getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
