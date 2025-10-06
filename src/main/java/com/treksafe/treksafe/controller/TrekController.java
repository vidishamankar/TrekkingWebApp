package com.treksafe.treksafe.controller;

import com.treksafe.treksafe.model.Weather; // CHANGED from Trail to Weather
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * REST Controller for handling Trail-related API requests.
 * @RestController marks this class as a component that handles web requests and returns data directly (typically JSON).
 * @RequestMapping defines the base path for all methods in this controller.
 */
@RestController
@RequestMapping("/api/v1/trails") // The base URL for all endpoints in this controller
public class TrekController {

    /**
     * Handles GET requests to /api/v1/trails
     * This API will return a list of all trekking trails.
     * In a real application, this would fetch data from a database (using a Service layer).
     *
     * @return A list of Weather objects wrapped in a ResponseEntity (HTTP response). // Updated documentation
     */
    @GetMapping // Maps HTTP GET requests to the /api/v1/trails endpoint
    public ResponseEntity<List<Weather>> getAllTrails() { // CHANGED from List<Trail> to List<Weather>
        // --- Mock Data (Replace this with database calls later) ---
        // CHANGED constructor calls from new Trail(...) to new Weather(...)
        List<Weather> trails = Arrays.asList(
                new Weather("Kalsubai Peak", "Maharashtra", "Difficult", 6.6, "Partly Cloudy", true),
                new Weather("Triund Trek", "Himachal Pradesh", "Moderate", 9.0, "Sunny", false),
                new Weather("Valley of Flowers", "Uttarakhand", "Easy", 5.0, "Light Rain", true)
        );

        // ResponseEntity.ok() returns an HTTP 200 OK status with the list of trails in the body.
        return ResponseEntity.ok(trails);
    }

    /**
     * FUTURE API ENDPOINT:
     * You can add a safety report endpoint here.
     *
     * @PostMapping("/report-incident")
     * public ResponseEntity<String> reportIncident(@RequestBody IncidentReport report) {
     * // Logic to save the incident to the database and potentially send alerts.
     * return ResponseEntity.status(201).body("Incident reported successfully.");
     * }
     */
}
