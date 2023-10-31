package ru.gamrekeli.API.Service.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.gamrekeli.API.Service.message.Message;
import ru.gamrekeli.API.Service.model.User;
import ru.gamrekeli.API.Service.service.FriendService;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducer {

    @Value("${kafka.topics.notification-topic}")
    private String topic;

    @Autowired
    private FriendService friendService;


    private Long messageNumber = 0L;

    private final KafkaTemplate<Object, Object> kafkaTemplate;

    public void sendMessage(User user) {
        List<User> friend;
        friend = friendService.getFriends(user.getUserId());
        while (messageNumber != 10_000) {
            messageNumber ++;
            Message message = Message.builder()
                    .messageId(messageNumber)
                    .user(user)
                    .time(new Date())
                    .text("Test message")
                    .userList(friend)
                    .build();
            kafkaTemplate.send(topic, String.valueOf(ThreadLocalRandom.current().nextLong()), message);
            log.info("Отправлено сообщение номер {}", messageNumber);
        }
    }
}
