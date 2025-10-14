// File: src/main/java/com/treksafe/treksafe/controller/HomeController.java
package com.treksafe.treksafe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/api/home")
    public String home() {
        return "Welcome to TrekSafe! Plan, explore, and stay safe on every adventure.";
    }
}
