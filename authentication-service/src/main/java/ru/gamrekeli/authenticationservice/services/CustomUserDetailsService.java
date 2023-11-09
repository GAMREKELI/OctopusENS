package ru.gamrekeli.authenticationservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.gamrekeli.authenticationservice.configuration.CustomUserDetails;
import ru.gamrekeli.authenticationservice.dto.AuthRequest;
import ru.gamrekeli.authenticationservice.entities.User;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<User> loginUser = Optional.ofNullable(restTemplate.postForObject("http://user-service/api/v1/user/login",
                AuthRequest.builder().login(username).build(), User.class));

        return loginUser.map(CustomUserDetails::new).orElseThrow(() ->
                new UsernameNotFoundException("user not found with name: " + username));
    }
}
