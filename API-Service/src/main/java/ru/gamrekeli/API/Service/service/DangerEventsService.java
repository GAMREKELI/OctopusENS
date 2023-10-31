package ru.gamrekeli.API.Service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import ru.gamrekeli.API.Service.message.Message;
import ru.gamrekeli.API.Service.model.DangerEvents;
import ru.gamrekeli.API.Service.model.User;
import ru.gamrekeli.API.Service.producer.KafkaProducer;
import ru.gamrekeli.API.Service.repository.DangerEventsRepository;
import ru.gamrekeli.API.Service.repository.UserRepository;
import ru.gamrekeli.API.Service.service.exceptions.UserNotFoundException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DangerEventsService {

    @Autowired
    private DangerEventsRepository dangerEventsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendService friendService;

    @Autowired //
    private KafkaProducer kafkaProducer; //

    public Message createDangerEvent(Long userId) throws UserNotFoundException {
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
//            DangerEvents dangerEvents = DangerEvents.builder()
//                    .time(time)
//                    .user(user.get())
//                    .build();
            dangerEventsRepository.save(dangerEvents);
        } catch (DataAccessException e) {
            System.out.println("Невозможно добавить событие об опасности");
        }

        List<User> friends;
        friends = friendService.getFriends(userId);
        kafkaProducer.sendMessage(user.get());
        return Message.builder()
                .messageId(dangerEvents.getDangerId()) //
                .user(user.get())
                .text("Тестовое сообщение")
                .time(time)
                .userList(friends)
                .build();
    }
}
