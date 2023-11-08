package ru.gamrekeli.authenticationservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.gamrekeli.authenticationservice.entities.AuthRequest;
import ru.gamrekeli.authenticationservice.entities.AuthResponse;
import ru.gamrekeli.authenticationservice.entities.User;

@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final JwtUtil jwtUtil;

//    private final UserClient userClient;

    private final RestTemplate restTemplate;


    public AuthResponse register(AuthRequest request) {

        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        System.out.println(request.toString());
        User registeredUser = restTemplate.postForObject("http://user-service/api/v1/user/registration", request, User.class);
//        User registeredUser = userClient.registration(request).getBody();



        log.info("<<<<<<<<<<<<<<<" + registeredUser.getUserId().toString() + " " + registeredUser.getUserId().toString() + ">>>>>>>>>>>>>>>>>>");

        String accessToken = jwtUtil.generate(registeredUser.getUserId().toString(), registeredUser.getRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(registeredUser.getUserId().toString(), registeredUser.getRole(), "REFRESH");

//        log.info("<<<<<<<<<<<<<<<" + accessToken + " " + refreshToken + ">>>>>>>>>>>>>>>>>>");
        return new AuthResponse(accessToken, refreshToken);
    }
}
