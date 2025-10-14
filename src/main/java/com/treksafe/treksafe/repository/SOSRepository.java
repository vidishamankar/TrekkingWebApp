package com.treksafe.treksafe.repository;
import com.treksafe.treksafe.model.SOSData;
import com.treksafe.treksafe.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the SOSData entity.
 * It provides standard CRUD operations and custom query methods.
 *
 * Extends JpaRepository, specifying the Entity (SOSData) and the Primary Key type (Long).
 */
@Repository
public interface SOSRepository extends JpaRepository<SOSData, Long> {

    /**
     * Custom query method to find SOSData associated with a specific User entity.
     * Spring Data JPA automatically generates the SQL based on the method name.
     *
     * @param user The User entity object.
     * @return An Optional containing the SOSData if found, or empty otherwise.
     */
    Optional<SOSData> findByUser(User user);
}
