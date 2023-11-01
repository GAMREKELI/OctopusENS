package ru.gamrekeli.notificationservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.gamrekeli.notificationservice.message.Message;
import ru.gamrekeli.notificationservice.model.User;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumerListeners {

//    @KafkaListener(
//            id = "consumer-group-1",
//            topics = "${kafka.topics.notification-topic}",
//            containerFactory = "kafkaListenerContainerFactory"
//    )
//    public void handler1(@Payload Message message) {
//        readMessage(message);
//
//    }
//
//    @KafkaListener(
//            id = "consumer-group-2",
//            topics = "${kafka.topics.notification-topic}",
//            containerFactory = "kafkaListenerContainerFactory"
//    )
//    public void handler2(@Payload Message message) {
//        readMessage(message);
//    }
//
//    public void readMessage(Message message) {
//        long number = message.getMessageId();
//        String currentThreadName = Thread.currentThread().getName();
//        log.info("Прочитано сообщение с номером: {} в потоке: {}", number, currentThreadName);
//        if (number % 100 == 0) {
//            log.info("Сообщение кратно 100");
//            throw new RuntimeException("Получено сообщение с номером кратным 100");
//        }
//    }
    private static final String topicCreateOrder = "${topics.notification-topic}";
    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";

    @Autowired
    private ObjectMapper objectMapper;


    @KafkaListener(
            topics = topicCreateOrder,
            groupId = kafkaConsumerGroupId,
            properties = {"spring.json.value.default.type=com.example.consumer.service.messaging.event.OrderEvent"}
    )
    public void createMessage(String new_message) throws JsonProcessingException {
        Message message = objectMapper.readValue(new_message, Message.class);
        log.info("<<<<<<<<<<<<<<<<<" + message.getMessageId() +
                message.getUser().toString() +
                message.getText() + message.getTime() +
                message.getUserList().toString() + ">>>>>>>>>>>>>>>>>>>>>");
    }
}
