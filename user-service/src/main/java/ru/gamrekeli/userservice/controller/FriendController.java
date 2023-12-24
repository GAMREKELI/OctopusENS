package ru.gamrekeli.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.gamrekeli.userservice.entity.FriendShip;
import ru.gamrekeli.userservice.entity.User;
import ru.gamrekeli.userservice.service.FriendServiceImpl;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/api/v1/user")
public class FriendController {

    @Autowired
    private FriendServiceImpl friendServiceImpl;

    @ResponseBody
    @GetMapping("/{userId}")
    public User showUser(@PathVariable("userId") Long userId) {
        return friendServiceImpl.getUser(userId);
    }

    @ResponseBody
    @GetMapping("/friends/{userId}")
    public List<User> showFriends(@PathVariable("userId") Long userId) {
        return friendServiceImpl.getFriends(userId);
    }

    @ResponseBody
    @GetMapping("/subscribers/{userId}")
    public List<User> showSubscribers(@PathVariable("userId") Long userId) {
        return friendServiceImpl.getSubscribers(userId);
    }

    @ResponseBody
    @PostMapping("/add/{userId}/{friendId}")
    public ResponseEntity<FriendShip> addFriend(@PathVariable("userId") Long userId,
                                                @PathVariable("friendId") Long friendId) throws NoSuchElementException {
        if (!userId.equals(friendId)) {
            friendServiceImpl.addFriend(userId, friendId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @PostMapping("/confirm/{userId}/{friendId}")
    public ResponseEntity<FriendShip> confirmationAddFriend(@PathVariable("userId") Long userId,
                                                            @PathVariable("friendId") Long friendId) {
        if (!userId.equals(friendId)) {
            friendServiceImpl.confirmationAddFriend(userId, friendId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
