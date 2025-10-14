package com.treksafe.treksafe.controller;

import com.treksafe.treksafe.model.Navigation;
import com.treksafe.treksafe.service.NavigationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/navigation")
public class NavigationController {

    private final NavigationService navigationService;

    public NavigationController(NavigationService navigationService) {
        this.navigationService = navigationService;
    }

    @GetMapping
    public List<Navigation> getAllNavigations() {
        return navigationService.getAllNavigations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Navigation> getNavigationById(@PathVariable Long id) {
        Navigation navigation = navigationService.getNavigationById(id);
        if (navigation != null) {
            return ResponseEntity.ok(navigation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Navigation createNavigation(@RequestBody Navigation navigation) {
        return navigationService.createNavigation(navigation);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Navigation> updateNavigation(@PathVariable Long id, @RequestBody Navigation navigation) {
        Navigation updatedNavigation = navigationService.updateNavigation(id, navigation);
        if (updatedNavigation != null) {
            return ResponseEntity.ok(updatedNavigation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNavigation(@PathVariable Long id) {
        boolean deleted = navigationService.deleteNavigation(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
