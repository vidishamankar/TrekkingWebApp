package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.Trail;
import com.treksafe.treksafe.repository.TrailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TrailService {

    private final TrailRepository trailRepository;
    // ... (other injected repositories like UserRepository, ReviewRepository)

    @Autowired
    public TrailService(TrailRepository trailRepository) {
        this.trailRepository = trailRepository;
        // ...
    }

    /**
     * Retrieves a trail by its ID. (This method fixes the error)
     * @param trailId The ID of the trail.
     * @return An Optional containing the Trail if found, or empty otherwise.
     */
    public Optional<Trail> getTrailDetails(Long trailId) {
        return trailRepository.findById(trailId);
    }

    // ... (Your other methods: addToUserTreks, submitNewReview)
}