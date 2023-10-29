package ru.gamrekeli.API.Service.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
    @SequenceGenerator(
            name = "sequence_user",
            sequenceName = "sequence_user",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_user"
    )
    private Long userId;

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

    @OneToMany
    @JoinColumn(
            name = "danger_events"
    )
    private List<DangerEvents> dangerEvents;
}
