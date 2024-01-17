package ru.gamrekeli.userservice.entity;

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
