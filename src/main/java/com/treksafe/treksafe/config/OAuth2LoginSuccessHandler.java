package com.treksafe.treksafe.config; // Or whatever package you use

import com.treksafe.treksafe.model.User;
import com.treksafe.treksafe.model.Role; // Your enum for roles (e.g., USER, ADMIN)
import com.treksafe.treksafe.service.JwtService;
import com.treksafe.treksafe.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtService jwtService;
    private final UserService userService; // Your service for User objects

    // Set this to the URL of your frontend (where your HTML files are)
    private final String frontendAppUrl = "http://localhost:5500/src/main/resources/static/landing.html";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        // 1. Get user details from Google
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");

        // 2. Find or create this user in your database
        //    (You must implement this logic in your UserService)
        User user = userService.findByEmail(email)
                .orElseGet(() -> {
                    // Create a new user if they don't exist
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(name);

                    // Make sure your User entity has a field for the picture URL
                    newUser.setProfilePictureUrl(picture);

                    newUser.setRole(Role.USER); // Set a default role

                    // Since it's OAuth, they don't have a password with you
                    // newUser.setPassword(null);

                    return userService.saveUser(newUser); // Save the new user
                });

        // 3. Create a JWT token for this user
        //    (Your JwtService must be able to generate a token from a User object
        //     and include the 'name' and 'picture' claims)
        String token = jwtService.generateToken(user);

        // 4. Redirect user back to your frontend app with the token
        String targetUrl = UriComponentsBuilder.fromUriString(frontendAppUrl)
                .queryParam("token", token)
                .build().toUriString();

        // This sends the redirect
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}