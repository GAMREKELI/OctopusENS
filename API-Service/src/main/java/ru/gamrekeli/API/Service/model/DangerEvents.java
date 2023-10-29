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
public class DangerEvents {

    @Id
    @SequenceGenerator(
            name = "sequence_danger",
            sequenceName = "sequence_danger",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_danger"
    )
    private Long dangerId;

    @Column(
            name = "time",
            nullable = false
    )
    private Date time;

}