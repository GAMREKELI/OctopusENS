package ru.gamrekeli.authenticationservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.authenticationservice.dto.AuthRequest;
import ru.gamrekeli.authenticationservice.entities.User;
import ru.gamrekeli.authenticationservice.services.AuthService;

@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/registration")
    public String registration(@RequestBody User request) {
        return authService.register(request);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest request) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(), request.getPassword()));

        if (authentication.isAuthenticated()) {
            return authService.generateToken(request.getLogin());
        } else {
            throw new RuntimeException("invalid access");
        }
    }

    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        authService.validateToken(token);
        return "Token is valid";
    }
}
