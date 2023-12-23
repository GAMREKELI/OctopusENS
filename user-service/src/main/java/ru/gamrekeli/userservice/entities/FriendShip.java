package ru.gamrekeli.userservice.entities;

import jakarta.persistence.*;
import lombok.*;
import ru.gamrekeli.userservice.entities.status.Status;

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
public class FriendShip {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long friendShipId;

    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user; // to

    @ManyToOne
    @JoinColumn(
            name = "friend_id"
    )
    private User friend; // from

    @Enumerated(EnumType.STRING)
    private Status status;

}