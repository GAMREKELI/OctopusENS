package ru.gamrekeli.authenticationservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gamrekeli.authenticationservice.dto.AuthRequest;
import ru.gamrekeli.authenticationservice.dto.AuthorizationRequest;
import ru.gamrekeli.authenticationservice.dto.ResponseToken;
import ru.gamrekeli.authenticationservice.entity.User;

@Service
@Slf4j
public class AuthService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public String register(AuthRequest request) {

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        User user = User.builder()
                .login(request.getLogin())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .role("USER") // fix
                .build();
        User registeredUser = restTemplate.postForObject("http://user-service/api/v1/user/registration", user, User.class);

        return "user added to the system";
    }

    public ResponseToken getUserId(AuthorizationRequest request) {
        User user = restTemplate.getForObject("http://user-service/api/v1/user/getuser?login=" + request.getLogin(), User.class);
        ResponseToken responseToken = ResponseToken.builder()
                .userId(user.getUserId())
                .login(user.getLogin())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .jwtToken(generateToken(request.getLogin()))
                .build();
        return responseToken;
    }
    public String generateToken(String login) {
        return jwtUtil.generateToken(login);
    }

    public void validateToken(String token) {
        jwtUtil.validateToken(token);
    }

}
