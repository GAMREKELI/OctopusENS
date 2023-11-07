package ru.gamrekeli.userservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.userservice.entities.FriendShip;
import ru.gamrekeli.userservice.entities.User;
import ru.gamrekeli.userservice.services.FriendService;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/api/v1/user")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @ResponseBody
    @GetMapping("/{userId}")
    public User showUser(@PathVariable("userId") Long userId) {
        return friendService.getUser(userId);
    }

    @ResponseBody
    @GetMapping("/friends/{userId}")
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
