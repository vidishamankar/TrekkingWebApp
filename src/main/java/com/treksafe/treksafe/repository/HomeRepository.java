package com.treksafe.treksafe.repository;

import com.treksafe.treksafe.model.Home;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HomeRepository extends JpaRepository<Home, Long> {
    List<Home> findByDifficulty(String difficulty);
}
