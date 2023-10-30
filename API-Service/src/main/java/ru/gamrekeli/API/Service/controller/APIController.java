package ru.gamrekeli.API.Service.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.API.Service.message.Message;
import ru.gamrekeli.API.Service.model.User;
import ru.gamrekeli.API.Service.service.DangerEventsService;
import ru.gamrekeli.API.Service.service.UserService;
import ru.gamrekeli.API.Service.service.exceptions.UserNotFoundException;

@Controller
@RequestMapping("/api/v1")
@AllArgsConstructor
public class APIController {

    @Autowired
    private UserService userService;

    @Autowired
    private DangerEventsService dangerEventsService;

    @ResponseBody
    @PostMapping("/registration")
    public ResponseEntity<User> regTest(@RequestBody User user) {
        return new ResponseEntity<> (userService.save(user), HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/send/{userId}")
    public ResponseEntity<Message> sendMessage(@PathVariable("userId") Long userId) throws UserNotFoundException {

        return new ResponseEntity<>(dangerEventsService.createDangerEvent(userId), HttpStatus.OK);
    }

}
