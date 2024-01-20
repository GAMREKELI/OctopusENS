package ru.gamrekeli.userservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserWithoutPass {
    private Long userId;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
