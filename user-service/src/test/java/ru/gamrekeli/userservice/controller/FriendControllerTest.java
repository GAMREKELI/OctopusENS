package ru.gamrekeli.userservice.controller;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Testcontainers
class FriendControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Container
    @ServiceConnection
    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(DockerImageName.parse("postgres:14.7-alpine"))
            .withInitScript("createDB_users_and_data_in_users.sql")
            .withInitScript("createDB_friendship_and_data_in_friendship.sql");


    @DynamicPropertySource
    static void configurationContainer(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @BeforeAll
    public static void startContainer() {
        postgresContainer.start();
    }

    @AfterAll
    public static void stopContainer() {
        postgresContainer.stop();
    }

    @Test
    void showUser() throws Exception{
        String response = """
                {
                "userId": 1,
                    "login": "Ivan",
                    "password": "$2y$10$7EvloDO2IuIeqJ82kMfKleK14iJL95wFVhvPY2rAxljpOV2Ih9sky",
                    "firstName": "Иван",
                    "lastName": "Тестовый",
                    "email": "test@gmail.com",
                    "phoneNumber": "+79105988089",
                    "role": "USER"
                }
                """;
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/{userId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andExpect(content().json(response));
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/user/1"))
                .andExpect(status().isOk())
                .andReturn();

        String content = result.getResponse().getContentAsString();
        System.out.println("Response content: " + content);

    }

    @Test
    void deleteFriend() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/delete/{userId}/{friendId}", 1L, 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/delete/{userId}/{friendId}", 1L, 3L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/delete/{userId}/{friendId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/delete/{userId}/{friendId}", 228L, 1337L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void confirmationAddFriend() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/confirm/{userId}/{friendId}", 3L, 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/confirm/{userId}/{friendId}", 2L, 4L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/confirm/{userId}/{friendId}", 4L, 5L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/confirm/{userId}/{friendId}", 1L, 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/user/confirm/{userId}/{friendId}", 1L, 2L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}