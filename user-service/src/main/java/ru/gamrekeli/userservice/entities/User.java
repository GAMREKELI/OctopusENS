package ru.gamrekeli.userservice.entities;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "email",
                columnNames = "email"
        )
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long userId;

    @Column(
            name = "login",
            nullable = false
    )
    private String login;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false

    )
    private String lastName;

    @Column(
            name = "email",
            nullable = false

    )
    private String email;

    @Column(
            name = "phone_number",
            nullable = false

    )
    private String phoneNumber;

    @Column(
            name = "role",
            nullable = false
    )
    private String role;
}
