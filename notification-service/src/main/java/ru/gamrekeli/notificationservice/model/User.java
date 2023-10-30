package ru.gamrekeli.notificationservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Data
@Builder
public class User {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
