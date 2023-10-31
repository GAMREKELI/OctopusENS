package ru.gamrekeli.notificationservice.consumer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.gamrekeli.notificationservice.message.Message;
import ru.gamrekeli.notificationservice.model.User;

@Component
@RequiredArgsConstructor
@Slf4j
public class KafkaConsumerListeners {

    @KafkaListener(
            id = "consumer-group-1",
            topics = "${kafka.topics.notification-topic}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handler1(@Payload Message message) {
        readMessage(message);

    }

    @KafkaListener(
            id = "consumer-group-2",
            topics = "${kafka.topics.notification-topic}",
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handler2(@Payload Message message) {
        readMessage(message);
    }

    public void readMessage(Message message) {
        long number = message.getMessageId();
        String currentThreadName = Thread.currentThread().getName();
        log.info("Прочитано сообщение с номером: {} в потоке: {}", number, currentThreadName);
        if (number % 100 == 0) {
            log.info("Сообщение кратно 100");
            throw new RuntimeException("Получено сообщение с номером кратным 100");
        }
    }
}
