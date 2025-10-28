package com.treksafe.treksafe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

/**
 * The core data model representing a registered user in the database.
 * @Entity indicates this class is mapped to a table.
 * Using Lombok's @Data for automatic getters, setters, toString, equals, and hashCode.
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email; // Used as the unique identifier/username for login

    // Store the HASHED password.
    // @JsonIgnore ensures the hashed password is never accidentally sent in a JSON response.
    @JsonIgnore
    @Column(nullable = false)
    private String passwordHash;

    private LocalDateTime createdAt = LocalDateTime.now();

    // --- Spring Security Compatibility Methods ---

    /**
     * Required by Spring Security's UserDetails contract for retrieving the password.
     * Maps the internal 'passwordHash' field to the required 'getPassword' method name.
     */
    public String getPassword() {
        return this.passwordHash;
    }

    /**
     * Required by Spring Security's UserDetails contract for retrieving the username.
     * Maps the internal 'email' field to the required 'getUsername' method name.
     */
    public String getUsername() {
        return this.email;
    }

    // Setter for password hash used during registration
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
}
