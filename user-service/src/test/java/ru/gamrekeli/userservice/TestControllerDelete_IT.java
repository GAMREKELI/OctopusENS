package ru.gamrekeli.userservice;


import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.gamrekeli.userservice.controller.FriendController;
import ru.gamrekeli.userservice.service.FriendServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(controllers = FriendController.class)
//@ActiveProfiles("test")
//@Transactional
//public class TestControllerDelete_IT {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    // Инжекция сервиса, если он непосредственно используется в контроллере
//    @Autowired
//    private FriendServiceImpl friendService;
//
//    @Test
//    public void testDeleteFriend() throws Exception {
//        // Предположим, что ваш метод deleteFriend требует аутентификации, и использует путь "/delete/{userId}/{friendId}"
//        Long userId = 1L;
//        Long friendId = 2L;
//
//        // Запрос на удаление друга с помощью MockMvc
//        mockMvc.perform(post("/api/v1/user/delete/{userId}/{friendId}", userId, friendId))
//                .andExpect(status().isOk());
//
//        // Проверка, что друг успешно удален (может отличаться в зависимости от вашей логики)
//        // Например, если deleteFriend возвращает число, можно сделать проверку, что возвращаемое значение не равно 0
//        int response = friendService.deleteFriend(userId, friendId);
//        assertNotEquals(1, response);
//    }
//}
