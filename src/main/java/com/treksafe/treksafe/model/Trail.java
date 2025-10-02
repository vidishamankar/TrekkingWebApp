package com.treksafe.treksafe.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "trails")
public class Trail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String difficulty; // e.g., "Difficult", "Moderate", "Easy"
    private String duration; // e.g., "2 Days / 1 Night"
    private String bestSeason; // e.g., "Sept - Feb"
    private String maxAltitude; // e.g., "1,646 m (5,400 ft)"
    @Column(columnDefinition = "TEXT") // Use TEXT for long descriptions
    private String description;
    private String startPoint;
    private String trekDistance;
    private String trekType;
    private double latitude;
    private double longitude;
    private String imageUrl; // URL for the trek header image
    private double rating; // Average user rating
    private int totalReviews;

    // We'll use simple string lists for Itinerary and Equipment for now
    @ElementCollection
    @CollectionTable(name = "trail_itinerary", joinColumns = @JoinColumn(name = "trail_id"))
    @Column(name = "itinerary_step")
    private List<String> itinerary;

    @ElementCollection
    @CollectionTable(name = "trail_equipment", joinColumns = @JoinColumn(name = "trail_id"))
    @Column(name = "equipment_item")
    private List<String> equipment;

    // You would typically have a separate Review entity related to Trail.
    // For simplicity of this single API, we'll omit the Review model for now.

    // Default constructor
    public Trail() {}

    // Getters and Setters (Omitted for brevity, but you must generate them in your IDE)
    // ...
    // ...

    // Example Getter for reference:
    public Long getId() {
        return id;
    }

    // Example Setter for reference:
    public void setId(Long id) {
        this.id = id;
    }
}