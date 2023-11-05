package ru.gamrekeli.notificationservice.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import ru.gamrekeli.notificationservice.message.Message;
import ru.gamrekeli.notificationservice.service.EmailService;

import java.text.SimpleDateFormat;

@Component
@Slf4j
@AllArgsConstructor
public class KafkaConsumerListeners {

    private static final String topicCreateOrder = "${topics.notification-topic}";
    private static final String kafkaConsumerGroupId = "${spring.kafka.consumer.group-id}";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmailService emailService;


    @KafkaListener(
            topics = topicCreateOrder,
            groupId = kafkaConsumerGroupId,
            properties = {"spring.json.value.default.type=com.example.consumer.service.messaging.event.OrderEvent"}
    )
    public void handle(String new_message) throws JsonProcessingException{
        createMessage(new_message);
    }

    public void createMessage(String new_message) {
        try {
            System.out.println(new_message);
            Message message = objectMapper.readValue(new_message, Message.class);
            emailService.sendMessage(message);
        } catch (JsonProcessingException e) {
            log.error("Error deserializing message");
        }
    }
}
