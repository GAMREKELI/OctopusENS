package ru.gamrekeli.API.Service.message;

import lombok.*;
import ru.gamrekeli.API.Service.model.User;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Message {
    private Long messageId;
    private User user;
    private String text;
    private Date time;
    private List<User> userList;
}
