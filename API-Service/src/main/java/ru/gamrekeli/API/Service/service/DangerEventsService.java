package ru.gamrekeli.API.Service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.gamrekeli.API.Service.producer.Producer;
import ru.gamrekeli.API.Service.message.Message;
import ru.gamrekeli.API.Service.model.DangerEvents;
import ru.gamrekeli.API.Service.model.User;
import ru.gamrekeli.API.Service.repository.DangerEventsRepository;
import ru.gamrekeli.API.Service.repository.UserRepository;
import ru.gamrekeli.API.Service.service.exceptions.UserNotFoundException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DangerEventsService {


    @Autowired
    private DangerEventsRepository dangerEventsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendService friendService;

    @Autowired //
    private Producer producer; //



    public Message createDangerEvent(Long userId) throws UserNotFoundException,
            JsonProcessingException
    {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("Пользователь с id " + userId + " не найден");
        }

        Date time = new Date();
        DangerEvents dangerEvents = DangerEvents.builder()
                .time(time)
                .user(user.get())
                .build();
        try {
            dangerEventsRepository.save(dangerEvents);
        } catch (DataAccessException e) {
            System.out.println("Невозможно добавить событие об опасности");
        }

        List<User> friends;
        friends = friendService.getFriends(userId);
        return producer.sendMessage(Message.builder()
                .messageId(dangerEvents.getDangerId())
                .user(user.get())
                .text("Тестовое сообщение")
                .time(time)
                .userList(friends)
                .build());
    }
}
