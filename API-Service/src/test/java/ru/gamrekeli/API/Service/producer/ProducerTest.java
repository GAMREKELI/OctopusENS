package ru.gamrekeli.API.Service.producer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;
import ru.gamrekeli.API.Service.message.Message;
import ru.gamrekeli.API.Service.model.User;

import java.time.Duration;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
public class ProducerTest {


    @Container
    private static final KafkaContainer kafkaContainer = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:6.2.1"))
            .withExposedPorts(9093);

    @Test
    public void testSendMessage() throws JsonProcessingException {

        String bootstrapServers = kafkaContainer.getBootstrapServers();
        String topicName = "test-topic";

        Properties propertiesProducer = new Properties();
        propertiesProducer.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        propertiesProducer.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        propertiesProducer.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        Producer producer = new KafkaProducer(propertiesProducer);

        User user = User.builder()
                .userId(1L)
                .login("User 1")
                .password("Test password")
                .firstName("Test firstname")
                .lastName("Test Lastname")
                .email("test@gmail.com")
                .phoneNumber("+79999999999")
                .build();

        List<User> userList = new ArrayList<>();
        for (int i = 0; i < 5; i ++) {
            userList.add(User.builder()
                    .userId((long) (i + 1))
                    .login("User " + (2 + i))
                    .password("Test password")
                    .firstName("Test firstname")
                    .lastName("Test Lastname")
                    .email("test" + (2 + i) + "@gmail.com")
                    .phoneNumber("+79999999999")
                    .build());
        }

        Message message = Message.builder()
                .messageId(1L)
                .user(user)
                .text("Test text")
                .time(new Date())
                .userList(userList)
                .build();

        ObjectMapper objectMapper = new ObjectMapper();
        String messageAsString = objectMapper.writeValueAsString(message);
        producer.send(new ProducerRecord(topicName, messageAsString));


        Properties propertiesConsumer = new Properties();
        propertiesConsumer.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        propertiesConsumer.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propertiesConsumer.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propertiesConsumer.put(ConsumerConfig.GROUP_ID_CONFIG, "group-java-test");
        propertiesConsumer.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        Consumer consumer = new KafkaConsumer<>(propertiesConsumer);
        consumer.subscribe(Arrays.asList(topicName));
        ConsumerRecords records = consumer.poll(Duration.ofMillis(10_000L));
        consumer.close();
        assertEquals(1, records.count());
    }
}
