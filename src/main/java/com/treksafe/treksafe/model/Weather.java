package com.treksafe.treksafe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing weather-related data, potentially linked to a trail.
 * NOTE: The fields currently reflect trail properties (name, location, difficulty)
 * but the class has been renamed to Weather per request.
 * This may require field updates in a future step.
 * @Entity marks this class as a JPA entity (a table in the database).
 * @Data (from Lombok) automatically generates getters, setters, toString(), hashCode(), and equals() methods.
 * @NoArgsConstructor (from Lombok) generates a constructor with no arguments.
 */
@Entity
@Data
@NoArgsConstructor
public class Weather {

    // Primary key for the database table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String difficulty; // Easy, Moderate, Difficult
    private double lengthKm;
    private String weatherCondition; // To link with your frontend
    private boolean requiresPermit;

    // Constructor to easily create a new Weather object (excluding 'id' as it's auto-generated)
    public Weather(String name, String location, String difficulty, double lengthKm, String weatherCondition, boolean requiresPermit) {
        this.name = name;
        this.location = location;
        this.difficulty = difficulty;
        this.lengthKm = lengthKm;
        this.weatherCondition = weatherCondition;
        this.requiresPermit = requiresPermit;
    }
}
