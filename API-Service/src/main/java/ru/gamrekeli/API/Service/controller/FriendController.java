package ru.gamrekeli.API.Service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.API.Service.model.FriendShip;
import ru.gamrekeli.API.Service.model.User;
import ru.gamrekeli.API.Service.service.FriendService;

import java.sql.SQLException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/api/v1/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @ResponseBody
    @GetMapping("/{userId}")
    public List<User> showFriends(@PathVariable("userId") Long userId) {
        return friendService.getFriends(userId);
    }

    @ResponseBody
    @GetMapping("/subscribers/{userId}")
    public List<User> showSubscribers(@PathVariable("userId") Long userId) {
        return friendService.getSubscribers(userId);
    }

    @ResponseBody
    @PostMapping("/add/{userId}/{friendId}")
    public ResponseEntity<FriendShip> addFriend(@PathVariable("userId") Long userId,
                                                @PathVariable("friendId") Long friendId) throws NoSuchElementException {
        if (!userId.equals(friendId)) {
            friendService.addFriend(userId, friendId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @PostMapping("/confirm/{userId}/{friendId}")
    public ResponseEntity<FriendShip> confirmationAddFriend(@PathVariable("userId") Long userId,
                                                            @PathVariable("friendId") Long friendId) {
        if (!userId.equals(friendId)) {
            friendService.confirmationAddFriend(userId, friendId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
