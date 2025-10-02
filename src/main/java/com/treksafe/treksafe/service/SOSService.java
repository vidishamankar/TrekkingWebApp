package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.SOSData;
import com.treksafe.treksafe.model.User;
import com.treksafe.treksafe.repository.SOSRepository;
import com.treksafe.treksafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service layer for handling SOS (Emergency Safety) data operations.
 * It manages the saving, updating, and retrieval of SOS data linked to a specific user.
 */
@Service
@RequiredArgsConstructor
public class SOSService {

    private final SOSRepository sosRepository;
    private final UserRepository userRepository;

    /**
     * Finds the user by email and retrieves their existing SOS data, if available.
     * @param userEmail The email of the currently authenticated user.
     * @return The SOSData entity or null if not found.
     */
    public SOSData getSOSDataByEmail(String userEmail) {
        // Find the user first
        Optional<User> userOptional = userRepository.findByEmail(userEmail);
        if (userOptional.isEmpty()) {
            // Should not happen if the user is authenticated, but handling it prevents NullPointerException
            return null;
        }

        User user = userOptional.get();

        // Check if SOS data already exists for this user
        Optional<SOSData> sosDataOptional = sosRepository.findByUser(user);
        return sosDataOptional.orElse(null);
    }

    /**
     * Saves new SOS data or updates existing SOS data for the authenticated user.
     * @param userEmail The email of the currently authenticated user.
     * @param request The SOSRequest DTO containing the data to save.
     */
    public void saveOrUpdateSOSData(String userEmail, SOSData request) {
        Optional<User> userOptional = userRepository.findByEmail(userEmail);

        if (userOptional.isEmpty()) {
            throw new IllegalStateException("Authenticated user not found.");
        }

        User user = userOptional.get();

        // 1. Check if SOS data already exists
        Optional<SOSData> existingData = sosRepository.findByUser(user);

        SOSData sosData;
        if (existingData.isPresent()) {
            // 2. Update existing data
            sosData = existingData.get();
        } else {
            // 3. Create new data and link it to the user
            sosData = new SOSData();
            sosData.setUser(user); // Link the data to the User Entity
        }

        // 4. Map DTO fields to Entity fields
        // Note: The SOSRequest's list of contacts needs to be converted to a JSON string or similar
        // before being set on the SOSData entity, depending on the implementation details.
        // For simplicity, we are assuming request fields map directly to entity fields (e.g., medical info, custom note).
        // Since the SOSRequest DTO is simple, we map the fields below:
        sosData.setMedicalInfo(request.getMedicalInfo());
        sosData.setCustomNote(request.getCustomNote());

        // For emergencyContacts, we assume the SOSRequest has a field that aligns with the SOSData entity.
        // Since we don't have the SOSRequest fields, we will assume a generic contacts JSON string for now.
        // NOTE: In a real app, complex conversion logic for contacts would go here.
        sosData.setEmergencyContacts(request.getContactsJson());

        // 5. Save the updated or new entity
        sosRepository.save(sosData);
    }
}
