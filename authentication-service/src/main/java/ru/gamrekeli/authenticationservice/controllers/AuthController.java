package ru.gamrekeli.authenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.authenticationservice.entities.AuthRequest;
import ru.gamrekeli.authenticationservice.entities.AuthResponse;
import ru.gamrekeli.authenticationservice.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> registration(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
