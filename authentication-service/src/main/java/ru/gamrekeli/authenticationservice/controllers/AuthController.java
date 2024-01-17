package ru.gamrekeli.authenticationservice.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.authenticationservice.dto.AuthRequest;
import ru.gamrekeli.authenticationservice.dto.AuthorizationRequest;
import ru.gamrekeli.authenticationservice.dto.ResponseToken;
import ru.gamrekeli.authenticationservice.services.AuthService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = {"http://localhost:5173"})
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/registration")
    public String registration(@RequestBody AuthRequest request) {
        return authService.register(request);
    }

    @PostMapping("/token")
    public ResponseEntity<ResponseToken> getToken(@RequestBody AuthorizationRequest request) {
        Authentication authentication = manager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getLogin(), request.getPassword()));

        if (authentication.isAuthenticated()) {
            ResponseToken responseToken = ResponseToken.builder()
                    .userId(authService.getUserId(request))
                    .jwtToken(authService.generateToken(request.getLogin()))
                    .build();
            return new ResponseEntity<>(responseToken, HttpStatus.OK);
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
