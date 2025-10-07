package com.treksafe.treksafe.repository;

import com.treksafe.treksafe.model.Navigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NavigationRepository extends JpaRepository<Navigation, Long> {
    // Add custom queries if needed
}
