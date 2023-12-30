package ru.gamrekeli.userservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.gamrekeli.userservice.controller.FriendController;
import ru.gamrekeli.userservice.service.FriendServiceImpl;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = FriendController.class)
@ActiveProfiles("test")
public class TestFriendController_IT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private FriendServiceImpl friendServiceImpl;

    @Test
    void whenValidInput_showFriends_theReturns200() throws Exception {
        mockMvc.perform(
                        get("/api/v1/user/friends/{userId}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void whenValidInput_showSubscribers_theReturns200() throws Exception {
        mockMvc.perform(
                        get("/api/v1/user/subscribers/{userId}", 1L))
                .andExpect(status().isOk());
    }

    @Test
    void whenValidInput_addFriend_theReturns200() throws Exception {
        mockMvc.perform(
                post("/api/v1/user/add/{userId}/{friendId}", 1L, 2L))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeleteFriend() throws Exception {
        // Предположим, что ваш метод deleteFriend требует аутентификации, и использует путь "/delete/{userId}/{friendId}"
        Long userId = 1L;
        Long friendId = 2L;

        // Запрос на удаление друга с помощью MockMvc
        mockMvc.perform(post("/api/v1/user/delete/{userId}/{friendId}", userId, friendId))
                .andExpect(status().isOk());

        // Проверка, что друг успешно удален (может отличаться в зависимости от вашей логики)
        // Например, если deleteFriend возвращает число, можно сделать проверку, что возвращаемое значение не равно 0
        int response = friendServiceImpl.deleteFriend(userId, friendId);
        assertEquals(1, response);
    }


}
