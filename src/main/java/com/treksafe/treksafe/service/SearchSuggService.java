package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.SearchSugg;
import com.treksafe.treksafe.repository.SearchSuggRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SearchSuggService {

    private final SearchSuggRepository repository;

    public SearchSuggService(SearchSuggRepository repository) {
        this.repository = repository;
    }

    public List<SearchSugg> getAll() {
        return repository.findAll();
    }

    public Optional<SearchSugg> getById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public SearchSugg create(SearchSugg s) {
        return repository.save(s);
    }

    @Transactional
    public Optional<SearchSugg> update(Long id, SearchSugg updated) {
        return repository.findById(id).map(existing -> {
            if (updated.getTerm() != null) existing.setTerm(updated.getTerm());
            if (updated.getType() != null) existing.setType(updated.getType());
            existing.setPopularity(updated.getPopularity());
            return repository.save(existing);
        });
    }

    @Transactional
    public boolean delete(Long id) {
        return repository.findById(id).map(s -> {
            repository.delete(s);
            return true;
        }).orElse(false);
    }

    public List<SearchSugg> searchByTerm(String term) {
        return repository.findByTermContainingIgnoreCase(term);
    }

    public List<SearchSugg> findByType(String type) {
        return repository.findByType(type);
    }
}
