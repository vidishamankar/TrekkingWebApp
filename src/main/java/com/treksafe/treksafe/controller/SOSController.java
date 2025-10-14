package com.treksafe.treksafe.controller;
import com.treksafe.treksafe.model.SOSData;
import com.treksafe.treksafe.model.SOSData;
import com.treksafe.treksafe.service.SOSService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for handling secure SOS (Emergency Safety) data endpoints.
 * All methods in this controller require the user to be authenticated.
 */
@RestController
@RequestMapping("/api/v1/sos")
@RequiredArgsConstructor
public class SOSController {

    private final SOSService sosService;

    /**
     * Saves or updates the user's SOS data (emergency contacts, medical info).
     * Requires authentication.
     * @param request DTO containing the SOS data from the form.
     * @param authentication The Spring Security object containing the currently logged-in user's details.
     * @return A success response.
     */
    @PreAuthorize("isAuthenticated()")
    @PostMapping
    public ResponseEntity<String> saveSOSData(@RequestBody SOSData request, Authentication authentication) {
        String userEmail = authentication.getName();
        sosService.saveOrUpdateSOSData(userEmail, request);
        return ResponseEntity.ok("SOS data saved successfully.");
    }

    /**
     * Retrieves the currently logged-in user's SOS data.
     * Requires authentication.
     * @param authentication The Spring Security object containing the currently logged-in user's details.
     * @return The user's SOSData object or a 404 if none is found.
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping
    public ResponseEntity<?> getSOSData(Authentication authentication) {
        String userEmail = authentication.getName();
        SOSData sosData = sosService.getSOSDataByEmail(userEmail);

        if (sosData != null) {
            return ResponseEntity.ok(sosData);
        } else {
            // Return an empty request model if no data exists, so the front-end doesn't break
            return ResponseEntity.ok(new SOSData());
        }
    }
}
