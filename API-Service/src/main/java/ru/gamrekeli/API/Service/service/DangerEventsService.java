package ru.gamrekeli.API.Service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.gamrekeli.API.Service.client.UserClient;
import ru.gamrekeli.API.Service.producer.Producer;
import ru.gamrekeli.API.Service.message.Message;
import ru.gamrekeli.API.Service.model.DangerEvent;
import ru.gamrekeli.API.Service.model.User;
import ru.gamrekeli.API.Service.repository.DangerEventRepository;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DangerEventsService {

    @Autowired
    private Producer producer;

    @Autowired
    private DangerEventRepository dangerEventRepository;

    @Autowired
    private UserClient userClient;

    @Autowired
    private UserServiceChecker userServiceChecker;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Value("${topics.notification-topic}")
    private String sendClientTopic;

    public Message createDangerEvent(Long userId) throws
            JsonProcessingException
    {
        User user;
        boolean checkService = userServiceChecker.isUserServiceAvailable();
        if (checkService) {
            user = userClient.showUser(userId);
            redisTemplate.opsForValue().set("user_" + userId, user); // проверяем кеш на наличие пользователя

        }
        else {
            log.info("<<<<<<<<<<<<<<<<<<<<< Значение пользователя нету! >>>>>>>>>>>>>>>>>>>>>");
            user = (User) redisTemplate.opsForValue().get("user_" + userId);
        }

        Date time = new Date();
        DangerEvent dangerEvents = DangerEvent.builder()
                .time(time)
                .user(user.getUserId())
                .build();

        try {
            dangerEventRepository.save(dangerEvents);
        } catch (DataAccessException e) {
            log.info("Невозможно добавить событие об опасности");
        }

        List<User> friends;
        if (checkService) {
            friends = userClient.showFriends(userId);
            redisTemplate.opsForValue().set("friends_" + userId, friends);
        }
        else {
            log.info("<<<<<<<<<<<<<<<<<<<<< Значений друзей нету! >>>>>>>>>>>>>>>>>>>>>");
            friends = (List<User>) redisTemplate.opsForValue().get("friends_" + userId);
        }
        return producer.sendMessage(Message.builder()
                .messageId(dangerEvents.getDangerId())
                .user(user)
                .text("Тестовое сообщение")
                .time(time)
                .userList(friends)
                .build(), sendClientTopic);
    }
}
