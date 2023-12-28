package ru.gamrekeli.userservice.service;

import ru.gamrekeli.userservice.entity.User;
import java.util.List;
import java.util.NoSuchElementException;

public interface FriendService {
    List<User> getSubscribers(Long userId);
    List<User> getFriends(Long userId);
    User getUser(Long userId);
    void addFriend(Long userId, Long friendId) throws NoSuchElementException;
    int confirmationAddFriend(Long userId, Long friendId);
    int deleteFriend(Long userId, Long friendId);
}
