package ru.gamrekeli.notificationservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import ru.gamrekeli.notificationservice.message.Message;
import ru.gamrekeli.notificationservice.model.User;

import java.text.SimpleDateFormat;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumerListeners {

    private static final String topicCreateOrder = "${topics.notification-topic}";
    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";

    @Autowired
    private ObjectMapper objectMapper;


    @KafkaListener(
            topics = topicCreateOrder,
            groupId = kafkaConsumerGroupId + "1",
            properties = {"spring.json.value.default.type=com.example.consumer.service.messaging.event.OrderEvent"},
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handle(String new_message) throws JsonProcessingException{
        createMessage(new_message);
//        log.info("<<<<<<<<<<<" + new_message + ">>>>>>>>>>>>>>");
    }

    @KafkaListener(
            topics = topicCreateOrder,
            groupId = kafkaConsumerGroupId + "2",
            properties = {"spring.json.value.default.type=com.example.consumer.service.messaging.event.OrderEvent"},
            containerFactory = "kafkaListenerContainerFactory"
    )
    public void handle2(String new_message) throws JsonProcessingException{
        createMessage(new_message);
//        log.info("<<<<<<<<<<<" + new_message + ">>>>>>>>>>>>>>");
    }

    public void createMessage(String new_message) {
        try {
            System.out.println(new_message);
            Message message = objectMapper.readValue(new_message, Message.class);
            log.info("<<<<<<<<<<<<<<<<<" + message.getMessageId() +
                    message.getUser().toString() +
                    message.getText() + message.getTime() +
                    message.getUserList().toString() + ">>>>>>>>>>>>>>>>>>>>>");
        } catch (JsonProcessingException e) {
            log.error("Error deserializing message");
        }
    }
}
