package ru.gamrekeli.authenticationservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gamrekeli.authenticationservice.entities.AuthRequest;
import ru.gamrekeli.authenticationservice.entities.AuthResponse;
import ru.gamrekeli.authenticationservice.services.AuthService;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @ResponseBody
    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> registration(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }
}
