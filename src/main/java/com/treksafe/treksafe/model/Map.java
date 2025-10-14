package com.treksafe.treksafe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing map-specific trail data.
 * This data would be used to render the trail map in the frontend (map.html).
 * @Entity marks this class as a JPA entity (a table in the database).
 */
@Entity
@Data
@NoArgsConstructor
public class Map {

    // Primary key for the database table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trailName; // e.g., Kalsubai Peak
    private String gpxFilePath; // Path to the GPX or GeoJSON file (mocked here)
    private double startLatitude;
    private double startLongitude;
    private double totalDistanceKm;
    private int elevationGainMeters;

    // Constructor for creating mock data
    public Map(String trailName, String gpxFilePath, double startLatitude, double startLongitude, double totalDistanceKm, int elevationGainMeters) {
        this.trailName = trailName;
        this.gpxFilePath = gpxFilePath;
        this.startLatitude = startLatitude;
        this.startLongitude = startLongitude;
        this.totalDistanceKm = totalDistanceKm;
        this.elevationGainMeters = elevationGainMeters;
    }
}
