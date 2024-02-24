package ru.gamrekeli.userservice.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users",
        uniqueConstraints = @UniqueConstraint(
                name = "uniqueColumn",
                columnNames = {"login", "email", "phone_number"}
        )
)
// step = 1
@GenericGenerator(name = "sUsersOctopusENS", parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "S_USERS")})
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sUsersOctopusENS"
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

    public UserWithoutPass toDTO() {
        return UserWithoutPass.builder()
                .userId(userId)
                .login(login)
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
    }
}
