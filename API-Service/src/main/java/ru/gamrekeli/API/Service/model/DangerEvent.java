package ru.gamrekeli.API.Service.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@Entity
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "danger_events"
)
public class DangerEvent {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long dangerId;

    @Column(
            name = "time",
            nullable = false
    )
    private Date time;

    @Column(
            name = "user_id",
            nullable = false
    )
    private Long user;
}
