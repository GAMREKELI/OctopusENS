package ru.gamrekeli.userservice.service;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
                listUsers.add(listSubscribe.get(i).getUser());
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
    public int addFriend(Long userId, Long friendId) throws NoSuchElementException{
        if (!userId.equals(friendId)) {
            int countRows = friendShipRepository.countFriendShipByUserIdAndFriendId(userId, friendId);
            if (countRows < 1) {
                friendShipRepository.save(
                        FriendShip.builder()
                                .user(userRepository.findById(userId).get())
                                .friend(userRepository.findById(friendId).get())
                                .status(Status.NO)
                                .build()
                );
                return 1;
            }
            else {
                log.info("<<<<<<<<<<<<<<<<<<<<<<" + " Связь с пользователями уже имеется " + ">>>>>>>>>>>>>>>>>>>>>>");

            }
        }
        return 0;
    }

    @Override
    public int confirmationAddFriend(Long userId, Long friendId)  {
        if (!userId.equals(friendId)) {
            int countForDelete = friendShipRepository.confirmationAddFriend(userId, friendId);
            if (countForDelete == 0) {
                log.info("<<<<<<<<<<<<<<<<<<<<<<" + " userId или friendId отсутствуют в базе " + ">>>>>>>>>>>>>>>>>>>>>>");
                return 0;
            } else {
                log.info("<<<<<<<<<<<<<<<<<<<<<<" + " Добавление в друзья прошло успешно " + ">>>>>>>>>>>>>>>>>>>>>>");
                return 1;
            }
        }
        return 0;
    }

    @Override
    public int deleteFriend(Long userId, Long friendId) {
        if (!userId.equals(friendId)) {
            int countForDelete = friendShipRepository.deleteFriend(userId, friendId);
            if (countForDelete == 0) {
                log.info("<<<<<<<<<<<<<<<<<<<<<<" + " userId или friendId отсутствуют в базе " + ">>>>>>>>>>>>>>>>>>>>>>");
                return 0;
            } else {
                friendShipRepository.replaceUserIdAndFriendId(userId, friendId);
                log.info("<<<<<<<<<<<<<<<<<<<<<<" + " Удаление прошло успешно " + ">>>>>>>>>>>>>>>>>>>>>>");
                return 1;
            }
        }
        return 0;
    }
}
