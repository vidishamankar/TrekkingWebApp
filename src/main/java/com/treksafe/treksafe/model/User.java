package com.treksafe.treksafe.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * The core User entity for the application.
 * Implements UserDetails from Spring Security to integrate with the authentication framework.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "_user") // Ensure the import for @Table is present
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Use 'fullName' for consistent naming across the DTO and Entity
    private String fullName;

    @Column(unique = true) // Ensure emails are unique
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    // --- UserDetails Methods Implementation ---

    /**
     * Returns the authorities granted to the user.
     * Fix: Ensures the return type is Collection<? extends GrantedAuthority>
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Correctly wraps the single role enum into a collection of SimpleGrantedAuthority objects
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    /**
     * Returns the username (which is the email in this case) used for authentication.
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * Account status checks (always return true for simplicity in this initial setup)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
