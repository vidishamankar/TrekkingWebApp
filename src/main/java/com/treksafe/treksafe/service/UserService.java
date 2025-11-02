package com.treksafe.treksafe.service;

import com.treksafe.treksafe.model.User;
import com.treksafe.treksafe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // This method fixes 'cannot find symbol method findByEmail'
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // This method fixes 'cannot find symbol method saveUser'
    public User saveUser(User user) {
        return userRepository.save(user);
    }
}