package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.EmergencyAlert;
import com.treksafe.treksafe.repository.EmergencyAlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service layer for managing business logic related to Emergency Alerts.
 * It coordinates the process of saving an alert and (in a full application)
 * could handle notifications or external service calls.
 */
@Service
public class EmergencyAlertService {

    private final EmergencyAlertRepository alertRepository;

    @Autowired
    public EmergencyAlertService(EmergencyAlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    /**
     * Saves a new emergency alert to the database.
     * @param alert The EmergencyAlert entity (with location data).
     * @return The saved EmergencyAlert entity with its generated ID and timestamp.
     */
    public EmergencyAlert saveAlert(EmergencyAlert alert) {
        // Future Logic:
        // 1. Send SMS/Email notifications to emergency contacts (SOS data).
        // 2. Log the event for the monitoring team.
        // 3. Mark the user's status as 'Distress'.
        return alertRepository.save(alert);
    }
}
