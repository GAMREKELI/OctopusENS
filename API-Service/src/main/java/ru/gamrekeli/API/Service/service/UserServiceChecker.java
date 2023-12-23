package ru.gamrekeli.API.Service.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
//@AllArgsConstructor
public class UserServiceChecker {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user-service.health-endpoint}")
    private String userServiceHealthEndpoint;

    public boolean isUserServiceAvailable() {
        try {
            System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<< " + userServiceHealthEndpoint + " >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            restTemplate.getForObject(userServiceHealthEndpoint, Void.class);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
