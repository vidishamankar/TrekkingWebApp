package com.treksafe.treksafe.model;

import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

/**
 * Represents a user's emergency and safety information stored in the database.
 * This entity is linked one-to-one with the User entity.
 */
@Entity
@Table(name = "sos_data")
@Data
@NoArgsConstructor
public class SOSData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Stores emergency contacts as a JSON string (List<Contact> format, handled by the front-end)
    // The @Type annotation from vladmihalcea is used to map a complex JSON structure
    // directly to a single column in PostgreSQL.
    @Type(JsonType.class)
    @Column(columnDefinition = "jsonb")
    private String emergencyContacts; // Stored as JSON string

    // Critical medical information (allergies, conditions, medications)
    @Column(columnDefinition = "TEXT")
    private String medicalInfo;

    // Any other custom notes relevant to safety or location
    @Column(columnDefinition = "TEXT")
    private String customNote;

    // One-to-One relationship with the User who owns this SOS data
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", unique = true, nullable = false)
    private User user;

    public String getContactsJson() {
    return customNote;
    }
}
