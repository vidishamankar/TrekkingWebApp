package com.treksafe.treksafe.repository;

import com.treksafe.treksafe.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Custom method to fetch reviews for a specific trail (useful for detail.html later)
    List<Review> findByTrailId(Long trailId);
}