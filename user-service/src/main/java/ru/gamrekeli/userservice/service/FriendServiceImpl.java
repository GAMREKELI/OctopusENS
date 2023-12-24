package ru.gamrekeli.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gamrekeli.userservice.entity.FriendShip;
import ru.gamrekeli.userservice.entity.User;
import ru.gamrekeli.userservice.entity.status.Status;
import ru.gamrekeli.userservice.repository.FriendShipRepository;
import ru.gamrekeli.userservice.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class FriendServiceImpl implements FriendService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendShipRepository friendShipRepository;

    @Override
    public List<User> getSubscribers(Long userId) {
        List<User> listUsers = new ArrayList<>();

        List<FriendShip> listSubscribe = friendShipRepository.findAllSubscribersByUserId(userId);
        int len = listSubscribe.size();
        if (len != 0) {
            for (int i = 0; i < len; i ++) {
                listUsers.add(listSubscribe.get(i).getFriend());
            }
        }
        return listUsers;
    }

    @Override
    public List<User> getFriends(Long userId) {
        List<User> listUsers = new ArrayList<>();

        List<FriendShip> listFriends = friendShipRepository.findAllFriendsByUserId(userId);
        int len = listFriends.size();
        if (len != 0) {
            for (int i = 0; i < len; i ++) {
                if (listFriends.get(i).getFriend().getUserId().equals(userId)) {
                    listUsers.add(listFriends.get(i).getUser());
                }
                else {
                    listUsers.add(listFriends.get(i).getFriend());
                }
            }
        }
        return listUsers;
    }

    @Override
    public User getUser(Long userId) {
        Optional<User> user = userRepository.findById(userId);

        return user.orElseGet(User::new);
    }

    @Override
    public void addFriend(Long userId, Long friendId) throws NoSuchElementException{
        friendShipRepository.save(
                FriendShip.builder()
                        .user(userRepository.findById(friendId).get())
                        .friend(userRepository.findById(userId).get())
                        .status(Status.NO)
                        .build()
        );
    }

    @Override
    public void confirmationAddFriend(Long userId, Long friendId)  {
        friendShipRepository.confirmationAddFriend(userId, friendId);
    }
}
