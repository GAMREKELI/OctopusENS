package ru.gamrekeli.authenticationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthorizationRequest {
    private String login;
    private String password;
}
