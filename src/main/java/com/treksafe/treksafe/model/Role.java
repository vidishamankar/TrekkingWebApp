package com.treksafe.treksafe.model;

/**
 * Defines the fixed set of roles a user can have within the application.
 * This is used for authorization and mapping to Spring Security authorities.
 */
public enum Role {
    // Standard application user with basic permissions
    USER,

    // Administrator user with elevated permissions
    ADMIN
}