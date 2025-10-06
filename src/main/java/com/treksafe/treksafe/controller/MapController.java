package com.treksafe.treksafe.controller;

import com.treksafe.treksafe.model.Map;
import com.treksafe.treksafe.service.MapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST Controller for handling Map-related API requests.
 * It uses the MapService to fetch trail map data.
 */
@RestController
@RequestMapping("/api/v1/maps") // Base URL for map data
public class MapController {

    private final MapService mapService;

    // Constructor Injection
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    /**
     * Handles GET requests to /api/v1/maps
     * Returns a list of all available trail maps.
     *
     * @return A list of Map objects.
     */
    @GetMapping
    public ResponseEntity<List<Map>> getAllMaps() {
        List<Map> maps = mapService.findAllMaps();
        return ResponseEntity.ok(maps);
    }

    /**
     * Handles GET requests to /api/v1/maps/{trailName}
     * Returns a specific trail map by name.
     *
     * @param trailName The name of the trail to search for.
     * @return The Map object or 404 Not Found.
     */
    @GetMapping("/{trailName}")
    public ResponseEntity<Map> getMapByTrailName(@PathVariable String trailName) {
        return mapService.findMapByTrailName(trailName)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
