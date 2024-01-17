package ru.gamrekeli.userservice.entity;

import jakarta.persistence.*;
import lombok.*;
import ru.gamrekeli.userservice.entity.status.Status;

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
    private User user; // from

    @ManyToOne
    @JoinColumn(
            name = "friend_id"
    )
    private User friend; // to

    @Enumerated(EnumType.STRING)
    private Status status;

}