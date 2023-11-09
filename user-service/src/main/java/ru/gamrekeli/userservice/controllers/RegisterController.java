package ru.gamrekeli.userservice.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.userservice.entities.User;
import ru.gamrekeli.userservice.services.UserService;

import java.util.Optional;


@Controller
@RequestMapping("/api/v1/user")
@AllArgsConstructor
@Slf4j
public class RegisterController {

    @Autowired
    private UserService userService;

    @ResponseBody
    @PostMapping("/registration")
    public ResponseEntity<User> registration(@RequestBody User user) {
        return new ResponseEntity<> (userService.save(user), HttpStatus.OK);
    }

//    @ResponseBody
//    @PostMapping("/login")
//    public ResponseEntity<User> login(@RequestBody UserRequest user) {
//        System.out.println("\n\n<<<<<<<<<<<<<<<" + user.getLogin() + ">>>>>>>>>>>>>>>>\n\n");
//        return new ResponseEntity<> (userService.getUser(user), HttpStatus.OK);
//    }

    @ResponseBody
    @PostMapping("/login")
    public ResponseEntity<Optional<User>> login(@RequestBody User user) {
        return new ResponseEntity<> (userService.getUser(user), HttpStatus.OK);
    }
}
