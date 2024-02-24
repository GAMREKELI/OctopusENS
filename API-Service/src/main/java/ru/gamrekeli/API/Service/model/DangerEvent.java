package ru.gamrekeli.API.Service.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "danger_events"
)
// step = 1
@GenericGenerator(name = "sDangerEventOctopusENS", parameters = {@org.hibernate.annotations.Parameter(name = "sequence", value = "S_DANGER")})
public class DangerEvent {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sDangerEventOctopusENS"
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
