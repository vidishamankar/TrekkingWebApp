package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.Blog;
import com.treksafe.treksafe.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service layer for Blog post management business logic.
 * @Service marks this class as a Spring component that holds business logic.
 */
@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
        // Initialize mock data when the service starts
        initializeMockData();
    }

    /**
     * Finds all blog posts, typically ordered by published date.
     * @return A list of all Blog objects.
     */
    public List<Blog> findAllPosts() {
        return blogRepository.findAll();
    }

    /**
     * Finds a specific blog post by its unique ID.
     * @param id The ID of the post.
     * @return An Optional containing the Blog object if found.
     */
    public Optional<Blog> findPostById(Long id) {
        return blogRepository.findById(id);
    }

    // Temporary initialization method to populate the database on startup
    private void initializeMockData() {
        if (blogRepository.count() == 0) {
            blogRepository.save(new Blog(
                    "10 Safety Essentials for a Himalayan Solo Trek",
                    "Arjun Sharma",
                    LocalDate.of(2025, 8, 23),
                    "Safety",
                    "/images/himalaya_safety.jpg",
                    "A detailed guide on what mandatory gear, medical supplies, and protocols you must carry when venturing into the Himalayan range alone."
            ));
            blogRepository.save(new Blog(
                    "First-Aid for the Indian Monsoon Trek",
                    "Priya Kulkarni",
                    LocalDate.of(2025, 8, 18),
                    "First-Aid",
                    "/images/monsoon_trek.jpg",
                    "Monsoon treks require special preparation. Learn how to treat common ailments like trench foot, fungal infections, and hypothermia."
            ));
            blogRepository.save(new Blog(
                    "Navigating the Sahyadris with a Map and Compass",
                    "Vikas Reddy",
                    LocalDate.of(2025, 8, 10),
                    "Gear",
                    "/images/sahyadri_map.jpg",
                    "Digital maps are great, but nothing beats the reliability of traditional navigation skills. Master the compass and topographical map."
            ));
        }
    }
}
