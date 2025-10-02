package com.treksafe.treksafe.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_trails")
public class UserTrail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link to the User who is adding the trail (Many UserTrails to One User)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Link to the Trail being added (Many UserTrails to One Trail)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trail_id", nullable = false)
    private Trail trail;

    // Status: To track if it's planned, completed, or a favorite
    private String status = "PLANNED";

    // Optional: Date/Time when the user added this
    private LocalDateTime addedDate = LocalDateTime.now();

    // Default constructor (required by JPA)
    public UserTrail() {}

    // Constructor for ease of creation
    public UserTrail(User user, Trail trail) {
        this.user = user;
        this.trail = trail;
    }

    // NOTE: You must generate all Getters and Setters for the fields above!

    // Example Getters/Setters:
    /*
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    // ... and so on for all fields ...
    */
}