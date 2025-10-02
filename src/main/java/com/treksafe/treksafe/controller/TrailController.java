package com.treksafe.treksafe.controller;

import com.treksafe.treksafe.model.Trail;
import com.treksafe.treksafe.service.TrailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/trails") // Base path for all trail-related APIs
public class TrailController {

    private final TrailService trailService;

    @Autowired
    public TrailController(TrailService trailService) {
        this.trailService = trailService;
    }

    /**
     * Endpoint to fetch the detailed information for a specific trail.
     * URL: /api/trails/{id}
     * Method: GET
     */
    @GetMapping("/{id}")
    public ResponseEntity<Trail> getTrailDetails(@PathVariable Long id) {
        Optional<Trail> trail = trailService.getTrailDetails(id);

        // Check if the trail exists
        if (trail.isPresent()) {
            return ResponseEntity.ok(trail.get()); // Return 200 OK with the Trail data
        } else {
            return ResponseEntity.notFound().build(); // Return 404 Not Found
        }
    }
}