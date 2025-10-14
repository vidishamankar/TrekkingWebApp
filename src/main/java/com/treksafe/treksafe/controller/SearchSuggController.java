package com.treksafe.treksafe.controller;

import com.treksafe.treksafe.model.SearchSugg;
import com.treksafe.treksafe.service.SearchSuggService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/searchsuggs")
public class SearchSuggController {

    private final SearchSuggService service;

    public SearchSuggController(SearchSuggService service) {
        this.service = service;
    }

    @GetMapping
    public List<SearchSugg> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SearchSugg> getById(@PathVariable Long id) {
        return service.getById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SearchSugg> create(@RequestBody SearchSugg s) {
        SearchSugg created = service.create(s);
        return ResponseEntity.created(URI.create("/api/searchsuggs/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SearchSugg> update(@PathVariable Long id, @RequestBody SearchSugg s) {
        return service.update(id, s).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean removed = service.delete(id);
        return removed ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<SearchSugg> search(@RequestParam String term) {
        return service.searchByTerm(term);
    }

    @GetMapping("/type/{type}")
    public List<SearchSugg> byType(@PathVariable String type) {
        return service.findByType(type);
    }
}
