package ru.gamrekeli.API.Service.model;

import lombok.*;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DangerEvents {

    private Long dangerId;
    private Date time;
    private User user;

}
