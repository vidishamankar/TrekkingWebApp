package com.treksafe.treksafe.repository;

import com.treksafe.treksafe.model.UserTrail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserTrailRepository extends JpaRepository<UserTrail, Long> {

    /**
     * Custom method to check if a specific user has already added a specific trail.
     * Spring Data JPA creates the query automatically based on the method name.
     * * @param userId The ID of the User
     * @param trailId The ID of the Trail
     * @return Optional<UserTrail>
     */
    Optional<UserTrail> findByUserIdAndTrailId(Long userId, Long trailId);
}