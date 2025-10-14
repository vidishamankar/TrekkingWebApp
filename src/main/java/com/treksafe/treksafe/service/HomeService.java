package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.Home;
import com.treksafe.treksafe.repository.HomeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {

    private final HomeRepository homeRepository;

    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public List<Home> getAllHomes() {
        return homeRepository.findAll();
    }

    public Home getHomeById(Long id) {
        return homeRepository.findById(id).orElse(null);
    }

    public Home createHome(Home home) {
        return homeRepository.save(home);
    }

    public Home updateHome(Long id, Home updatedHome) {
        return homeRepository.findById(id).map(home -> {
            home.setName(updatedHome.getName());
            home.setLocation(updatedHome.getLocation());
            home.setDistanceKm(updatedHome.getDistanceKm());
            home.setDifficulty(updatedHome.getDifficulty());
            return homeRepository.save(home);
        }).orElse(null);
    }

    public boolean deleteHome(Long id) {
        return homeRepository.findById(id).map(home -> {
            homeRepository.delete(home);
            return true;
        }).orElse(false);
    }

    public List<Home> getHomesByDifficulty(String difficulty) {
        return homeRepository.findByDifficulty(difficulty);
    }
}
