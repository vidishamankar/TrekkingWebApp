package com.treksafe.treksafe.controller;

import com.treksafe.treksafe.model.EmergencyAlert;
import com.treksafe.treksafe.service.EmergencyAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling emergency and safety-critical functions.
 * All endpoints here require user authentication.
 */
@RestController
@RequestMapping("/api/v1/emergency")
public class EmergencyController {

    private final EmergencyAlertService alertService;

    @Autowired
    public EmergencyController(EmergencyAlertService alertService) {
        this.alertService = alertService;
    }

    /**
     * POST /api/v1/emergency/alert - Triggers an SOS alert and saves the user's location.
     * Requires authentication (handled by Spring Security).
     * * @param alert The EmergencyAlert object containing latitude and longitude.
     * @return A response indicating the success of the alert.
     */
    @PostMapping("/alert")
    public ResponseEntity<?> triggerEmergencyAlert(@RequestBody EmergencyAlert alert) {

        // --- Security Integration Placeholder ---
        // In a secured environment, you would retrieve the logged-in User ID here:
        // Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        // alert.setUserId(userId);

        // --- TEMPORARY for initial testing with dummy data ---
        // Use a placeholder ID until security is implemented
        alert.setUserId(1L);

        EmergencyAlert savedAlert = alertService.saveAlert(alert);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse("success", "Emergency alert triggered and location saved successfully.", savedAlert)
        );
    }

    // Helper class for standardized JSON API responses
    private static class ApiResponse {
        public String status;
        public String message;
        public EmergencyAlert data;

        public ApiResponse(String status, String message, EmergencyAlert data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }
    }
}

