package ru.gamrekeli.API.Service.producer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.gamrekeli.API.Service.message.Message;

@Component
@RequiredArgsConstructor
@Slf4j
public class Producer {

//    @Autowired
    private final ObjectMapper objectMapper;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public Message sendMessage(Message message, String sendClientTopic) throws JsonProcessingException {
        String messageAsString = objectMapper.writeValueAsString(message);

        kafkaTemplate.send(sendClientTopic, messageAsString);
        log.info("Message send!");
        return message;
    }
}
