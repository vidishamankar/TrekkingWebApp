package com.treksafe.treksafe.model;

/**
 * Data Transfer Object (DTO) for handling user registration requests.
 * Captures all fields from the SIGN UP FORM.
 */
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
    private String confirmPassword; // Added for comparison on the client/server side

    // Default constructor
    public RegisterRequest() {}

    // Getters and Setters

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
