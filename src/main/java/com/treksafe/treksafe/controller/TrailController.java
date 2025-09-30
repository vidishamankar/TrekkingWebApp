package com.treksafe.treksafe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * This class serves as the API entry point for trail-related requests.
 * @RestController combines @Controller and @ResponseBody, meaning the returned
 * objects are automatically converted to JSON format for the web response.
 */
@RestController
public class TrailController {

    /**
     * Handles HTTP GET requests to the path /api/v1/status.
     * This is a simple health check to confirm the backend is running.
     *
     * @return A map containing a status message.
     */
    @GetMapping("/api/v1/status")
    public Map<String, String> getAppStatus() {
        Map<String, String> response = new HashMap<>();
        response.put("service", "TrekSafe Backend API");
        response.put("status", "Running successfully!");
        response.put("version", "1.0");
        return response;
    }

    // In the future, you will add methods like:
    // @GetMapping("/api/v1/trails") public List<Trail> getAllTrails() {...}
    // @PostMapping("/api/v1/reports") public SafetyReport submitSafetyReport(@RequestBody SafetyReport report) {...}
}
