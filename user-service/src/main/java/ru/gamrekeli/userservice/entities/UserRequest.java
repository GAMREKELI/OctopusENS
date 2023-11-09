package ru.gamrekeli.userservice.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class UserRequest {

    private String login;
    private String password;
}
