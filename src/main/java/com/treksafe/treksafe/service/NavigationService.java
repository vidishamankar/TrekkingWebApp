package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.Navigation;
import com.treksafe.treksafe.repository.NavigationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavigationService {

    private final NavigationRepository navigationRepository;

    public NavigationService(NavigationRepository navigationRepository) {
        this.navigationRepository = navigationRepository;
    }

    public List<Navigation> getAllNavigations() {
        return navigationRepository.findAll();
    }

    public Navigation getNavigationById(Long id) {
        return navigationRepository.findById(id).orElse(null);
    }

    public Navigation createNavigation(Navigation navigation) {
        return navigationRepository.save(navigation);
    }

    public Navigation updateNavigation(Long id, Navigation updatedNavigation) {
        return navigationRepository.findById(id).map(navigation -> {
            navigation.setDirection(updatedNavigation.getDirection());
            navigation.setLatitude(updatedNavigation.getLatitude());
            navigation.setLongitude(updatedNavigation.getLongitude());
            navigation.setDescription(updatedNavigation.getDescription());
            return navigationRepository.save(navigation);
        }).orElse(null);
    }

    public boolean deleteNavigation(Long id) {
        return navigationRepository.findById(id).map(navigation -> {
            navigationRepository.delete(navigation);
            return true;
        }).orElse(false);
    }
}
