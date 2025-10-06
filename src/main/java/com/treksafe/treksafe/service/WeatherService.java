package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.Weather;
import com.treksafe.treksafe.repository.WeatherRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer for trail and weather data business logic.
 * @Service marks this class as a Spring component that holds business logic.
 */
@Service
public class WeatherService {

    // Inject the Repository dependency
    private final WeatherRepository weatherRepository;

    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
        // Initialize mock data when the service starts (for demonstration with H2)
        initializeMockData();
    }

    // Method called by the controller to get all trails
    public List<Weather> findAllTrails() {
        // In a real scenario, this would be: return weatherRepository.findAll();
        return weatherRepository.findAll();
    }

    // Temporary initialization method to populate the database on startup
    private void initializeMockData() {
        if (weatherRepository.count() == 0) {
            weatherRepository.save(new Weather("Kalsubai Peak", "Maharashtra", "Difficult", 6.6, "Partly Cloudy", true));
            weatherRepository.save(new Weather("Triund Trek", "Himachal Pradesh", "Moderate", 9.0, "Sunny", false));
            weatherRepository.save(new Weather("Valley of Flowers", "Uttarakhand", "Easy", 5.0, "Light Rain", true));
        }
    }
}
