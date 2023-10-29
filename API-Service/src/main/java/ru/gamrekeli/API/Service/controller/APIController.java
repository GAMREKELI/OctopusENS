package ru.gamrekeli.API.Service.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.API.Service.model.User;
import ru.gamrekeli.API.Service.service.UserService;

@Controller
@RequestMapping("/api/v1")
@AllArgsConstructor
public class APIController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/registration")
    public ResponseEntity<User> regTest(@RequestBody User user) {
        return new ResponseEntity<> (userService.save(user), HttpStatus.OK);
    }


}
