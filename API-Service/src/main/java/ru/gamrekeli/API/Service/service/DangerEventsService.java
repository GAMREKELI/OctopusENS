package ru.gamrekeli.API.Service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gamrekeli.API.Service.client.UserClient;
import ru.gamrekeli.API.Service.producer.Producer;
import ru.gamrekeli.API.Service.message.Message;
import ru.gamrekeli.API.Service.model.DangerEvents;
import ru.gamrekeli.API.Service.model.User;
import ru.gamrekeli.API.Service.service.exceptions.UserNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DangerEventsService {

    @Autowired //
    private Producer producer; //

    @Autowired
    private UserClient userClient;

    public Message createDangerEvent(Long userId) throws UserNotFoundException,
            JsonProcessingException
    {
        User user = userClient.showUser(userId); // получение значений из user-service
        if (user.getLogin().isEmpty()) {
            throw new UserNotFoundException("Пользователь с id " + userId + " не найден");
        }

        Date time = new Date();
        DangerEvents dangerEvents = DangerEvents.builder()
                .time(time)
                .user(user)
                .build();
        /*
            Тут необходимо будет добавить отправку сообщения через kafka, для добавления
            события об опасности в БД
        */

//        try {
//            dangerEventsRepository.save(dangerEvents);
//        } catch (DataAccessException e) {
//            System.out.println("Невозможно добавить событие об опасности");
//        }

        List<User> friends;
        friends = userClient.showFriends(userId);
        return producer.sendMessage(Message.builder()
                .messageId(dangerEvents.getDangerId())
                .user(user)
                .text("Тестовое сообщение")
                .time(time)
                .userList(friends)
                .build());
    }
}
