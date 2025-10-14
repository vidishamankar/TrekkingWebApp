package com.treksafe.treksafe.repository;

import com.treksafe.treksafe.model.Weather;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for the Weather (Trail) entity.
 * JpaRepository provides ready-to-use methods like findAll(), findById(), save(), delete(), etc.
 * The parameters are: <Entity Class, Primary Key Type>
 */
@Repository // Optional, but good practice
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    // You can add custom query methods here, e.g.:
    // List<Weather> findByDifficulty(String difficulty);
}
