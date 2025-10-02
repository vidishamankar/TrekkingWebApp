package com.treksafe.treksafe.repository;

import com.treksafe.treksafe.model.Trail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrailRepository extends JpaRepository<Trail, Long> {

    // JpaRepository provides all basic CRUD methods, including:
    // Optional<Trail> findById(Long id);
    // Trail save(Trail trail);
}