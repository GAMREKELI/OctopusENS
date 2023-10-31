package ru.gamrekeli.notificationservice.message;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.gamrekeli.notificationservice.model.User;

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
