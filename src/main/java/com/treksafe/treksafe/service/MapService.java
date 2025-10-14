package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.Map;
import com.treksafe.treksafe.repository.MapRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service layer for map and trail data business logic.
 * @Service marks this class as a Spring component that holds business logic.
 */
@Service
public class MapService {

    // Inject the Repository dependency
    private final MapRepository mapRepository;

    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
        // Initialize mock data when the service starts (for demonstration with H2)
        initializeMockData();
    }

    /**
     * Finds all trail maps stored in the database.
     * @return A list of all Map objects.
     */
    public List<Map> findAllMaps() {
        return mapRepository.findAll();
    }

    /**
     * Finds a specific map by its trail name.
     * @param trailName The name of the trail.
     * @return An Optional containing the Map object if found.
     */
    public Optional<Map> findMapByTrailName(String trailName) {
        return mapRepository.findByTrailName(trailName);
    }

    // Temporary initialization method to populate the database on startup
    private void initializeMockData() {
        if (mapRepository.count() == 0) {
            // Mock data for the trails mentioned earlier
            mapRepository.save(new Map("Kalsubai Peak", "/gpx/kalsubai.gpx", 19.4678, 73.7176, 6.6, 950));
            mapRepository.save(new Map("Triund Trek", "/gpx/triund.gpx", 32.2599, 76.3242, 9.0, 1100));
            mapRepository.save(new Map("Valley of Flowers", "/gpx/valley.gpx", 30.7279, 79.5937, 5.0, 400));
        }
    }
}
