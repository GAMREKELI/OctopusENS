package ru.gamrekeli.userservice.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.userservice.model.User;
import ru.gamrekeli.userservice.service.UserService;


@Controller
@RequestMapping("/api/v1/user")
@AllArgsConstructor
public class RegisterController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/registration")
    public ResponseEntity<User> regTest(@RequestBody User user) {
        return new ResponseEntity<> (userService.save(user), HttpStatus.OK);
    }
}
