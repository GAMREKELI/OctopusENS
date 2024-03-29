package ru.gamrekeli.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ResponseToken {
    private Long userId;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String jwtToken;
}
