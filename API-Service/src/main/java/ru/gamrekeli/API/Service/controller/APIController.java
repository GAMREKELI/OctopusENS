package ru.gamrekeli.API.Service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.API.Service.message.Message;
import ru.gamrekeli.API.Service.service.DangerEventsService;
import ru.gamrekeli.API.Service.service.exceptions.UserNotFoundException;

@Controller
@RequestMapping("/api/v1/send/")
@AllArgsConstructor
public class APIController {

    @Autowired
    private DangerEventsService dangerEventsService;

    @ResponseBody
    @PostMapping("/{userId}")
    public ResponseEntity<Message> sendMessage(@PathVariable("userId") Long userId) throws UserNotFoundException,
            JsonProcessingException
    {
        return new ResponseEntity<>(dangerEventsService.createDangerEvent(userId), HttpStatus.OK);
    }
}
