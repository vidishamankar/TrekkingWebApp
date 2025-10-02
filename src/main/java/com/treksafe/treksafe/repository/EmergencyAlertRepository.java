package com.treksafe.treksafe.repository;

import com.treksafe.treksafe.model.EmergencyAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for data access operations on the EmergencyAlert entity.
 * JpaRepository provides basic CRUD operations.
 */
@Repository
public interface EmergencyAlertRepository extends JpaRepository<EmergencyAlert, Long> {

    // Custom query methods can be added here if needed,
    // e.g., to find all alerts for a specific user.
}
