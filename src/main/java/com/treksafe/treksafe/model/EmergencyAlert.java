package com.treksafe.treksafe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

/**
 * Data model for storing a user's emergency alert and their current location.
 * This entity will be mapped to a table in the PostgreSQL database.
 */
@Entity
public class EmergencyAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // This will hold the ID of the user who triggered the alert.
    // In a secured application, this is automatically retrieved from the JWT token.
    private Long userId;

    private double latitude;
    private double longitude;

    // Automatically set the timestamp when the alert is created in the database.
    private LocalDateTime timestamp = LocalDateTime.now();

    // Default constructor is required by JPA
    public EmergencyAlert() {}

    // Constructor used when creating an alert from the controller request
    public EmergencyAlert(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    // This setter is crucial and will be used by the service layer
    // to attach the correct user ID before saving.
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

