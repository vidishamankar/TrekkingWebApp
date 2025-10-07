package com.treksafe.treksafe.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

/**
 * Entity class representing a single Blog Post for the TrekSafe Blog page.
 * @Entity marks this class as a JPA entity (a table in the database).
 */
@Entity
@Data
@NoArgsConstructor
public class Blog {

    // Primary key for the database table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;
    private LocalDate publishedDate;
    private String category; // e.g., Safety, Gear, Stories
    private String imageUrl; // URL or path for a feature image

    // Using @Column(columnDefinition = "TEXT") to allow for larger text content
    @Column(columnDefinition = "TEXT")
    private String contentSnippet;

    // Full constructor for creating mock data
    public Blog(String title, String author, LocalDate publishedDate, String category, String imageUrl, String contentSnippet) {
        this.title = title;
        this.author = author;
        this.publishedDate = publishedDate;
        this.category = category;
        this.imageUrl = imageUrl;
        this.contentSnippet = contentSnippet;
    }
}
