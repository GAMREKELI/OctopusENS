package ru.gamrekeli.authenticationservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gamrekeli.authenticationservice.entities.User;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(User request) {

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User registeredUser = restTemplate.postForObject("http://user-service/api/v1/user/registration", request, User.class);

        return "user added to the system";
    }

    public String generateToken(String login) {
        return jwtUtil.generateToken(login);
    }

    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }

}
