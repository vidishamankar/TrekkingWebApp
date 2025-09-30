package com.treksafe.treksafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main class that starts your Spring Boot application.
 * The @SpringBootApplication annotation is essential:
 * 1. @Configuration: Tags the class as a source of bean definitions for the application context.
 * 2. @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings.
 * 3. @ComponentScan: Tells Spring to look for other components, configurations, and services
 * in the com.treksafe.treksafe package, allowing it to find your controllers and services.
 */
@SpringBootApplication
public class TrekSafeApplication {

    public static void main(String[] args) {
        // This command runs the entire Spring Boot application and starts the embedded Tomcat server.
        SpringApplication.run(TrekSafeApplication.class, args);
    }

}
