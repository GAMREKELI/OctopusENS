package ru.gamrekeli.API.Service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gamrekeli.API.Service.model.FriendShip;
import ru.gamrekeli.API.Service.model.User;
import ru.gamrekeli.API.Service.repository.FriendShipRepository;
import ru.gamrekeli.API.Service.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FriendShipRepository friendShipRepository;

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

    public List<User> getFriends(Long userId) {
        List<User> listUsers = new ArrayList<>();

        List<FriendShip> listFriends = friendShipRepository.findAllFriendsByUserId(userId);
        int len = listFriends.size();
        if (len != 0) {
            for (int i = 0; i < len; i ++) {
                listUsers.add(listFriends.get(i).getFriend());
            }
        }
        return listUsers;
    }

//    public void addFriend() {
//
//    }
}
