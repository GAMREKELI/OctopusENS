package ru.gamrekeli.notificationservice.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User {
    private Long userId;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
}
