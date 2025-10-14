package com.treksafe.treksafe.repository;

import com.treksafe.treksafe.model.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for the Map entity.
 * Provides standard CRUD operations for map data.
 */
@Repository
public interface MapRepository extends JpaRepository<Map, Long> {
    // Custom query to find a map by its trail name
    Optional<Map> findByTrailName(String trailName);
}
