package com.treksafe.treksafe.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Link back to the Trail being reviewed
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trail_id", nullable = false)
    private Trail trail; // <-- Field requires getTrail() and setTrail()

    // Link back to the User who submitted the review
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // <-- Field requires getUser() and setUser()

    // Core Review Fields
    private int overallRating;
    private String reviewTitle;
    @Column(columnDefinition = "TEXT")
    private String reviewDetails;
    private String proTip;

    // Trek Details
    private LocalDate trekDate;
    private String actualDuration;
    private String difficultyAssessment;

    // Specific Trail Conditions
    private String trailMarkings;
    private String crowdLevels;
    private String cleanliness;

    // Tags
    @ElementCollection
    @CollectionTable(name = "review_tags", joinColumns = @JoinColumn(name = "review_id"))
    @Column(name = "tag")
    private List<String> tags;

    // Audit/Time Stamp
    private LocalDateTime submissionDate = LocalDateTime.now();


    // Default constructor (required by JPA)
    public Review() {}

    // ------------------------------------------------------------------
    // ESSENTIAL GETTERS AND SETTERS TO FIX THE ERROR
    // ------------------------------------------------------------------

    // Getter and Setter for 'user'
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    // Getter and Setter for 'trail'
    public Trail getTrail() {
        return trail;
    }
    public void setTrail(Trail trail) {
        this.trail = trail;
    }

    // ------------------------------------------------------------------
    // You MUST include Getters and Setters for ALL other fields as well
    // (e.g., getId, setOverallRating, getReviewDetails, etc.)
    // for the @RequestBody mapping in the controller to work.
    // ------------------------------------------------------------------

    // Example of other required setters/getters:

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getOverallRating() { return overallRating; }
    public void setOverallRating(int overallRating) { this.overallRating = overallRating; }

    public String getReviewTitle() { return reviewTitle; }
    public void setReviewTitle(String reviewTitle) { this.reviewTitle = reviewTitle; }

    // ... (include all other setters/getters for a complete entity)
}