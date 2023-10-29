package ru.gamrekeli.API.Service.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gamrekeli.API.Service.model.User;

import java.util.List;

@Controller
@RequestMapping("/api/v1/friend")
public class FriendController {

    @ResponseBody
    @GetMapping("/{userId}")
    public List<User> showFriends(@RequestBody String login) {

    }
}
