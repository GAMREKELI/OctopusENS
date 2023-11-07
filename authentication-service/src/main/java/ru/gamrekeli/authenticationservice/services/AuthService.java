package ru.gamrekeli.authenticationservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gamrekeli.authenticationservice.client.UserClient;
import ru.gamrekeli.authenticationservice.entities.AuthRequest;
import ru.gamrekeli.authenticationservice.entities.AuthResponse;
import ru.gamrekeli.authenticationservice.entities.User;

@Service
@AllArgsConstructor
public class AuthService {

    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;

    @Autowired
    private UserClient userClient;

    @Autowired
    private ObjectMapper objectMapper;

    public AuthResponse register(AuthRequest request) {

        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        User registeredUser = userClient.registration(request).getBody();

        String accessToken = jwtUtil.generate(registeredUser.getUserId().toString(), registeredUser.getRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(registeredUser.getUserId().toString(), registeredUser.getRole(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }
}
