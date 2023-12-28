package ru.gamrekeli.userservice.entity;


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
