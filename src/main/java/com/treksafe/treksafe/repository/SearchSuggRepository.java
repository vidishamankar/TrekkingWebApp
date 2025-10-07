package com.treksafe.treksafe.repository;

import com.treksafe.treksafe.model.SearchSugg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchSuggRepository extends JpaRepository<SearchSugg, Long> {
    List<SearchSugg> findByTermContainingIgnoreCase(String partialTerm);
    List<SearchSugg> findByType(String type);
}
