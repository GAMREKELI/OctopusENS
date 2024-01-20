package ru.gamrekeli.userservice.service;

import ru.gamrekeli.userservice.entity.User;
import ru.gamrekeli.userservice.entity.UserWithoutPass;

import java.util.List;
import java.util.NoSuchElementException;

public interface FriendService {
    List<UserWithoutPass> getSubscribers(Long userId);
    List<UserWithoutPass> getFriends(Long userId);
    User getUser(Long userId);
    int addFriend(Long userId, Long friendId) throws NoSuchElementException;
    int confirmationAddFriend(Long userId, Long friendId);
    int deleteFriend(Long userId, Long friendId);
}
