package ru.gamrekeli.API.Service.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(
        name = "friendship"
)
public class Friendship {

    @Id
    @SequenceGenerator(
            name = "sequence_friend",
            sequenceName = "sequence_friend",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE
    )
    private Long friendShipId;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;

    @ManyToOne
    @JoinColumn(
            name = "friend_id"
    )
    private User friend;
}
