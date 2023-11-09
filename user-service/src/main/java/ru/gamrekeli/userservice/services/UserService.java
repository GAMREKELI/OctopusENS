package ru.gamrekeli.userservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gamrekeli.userservice.entities.User;
import ru.gamrekeli.userservice.entities.UserRequest;
import ru.gamrekeli.userservice.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {

        User newUser = User.builder()
                .login(user.getLogin())
                .password(user.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .role("USER") // Static
                .build();
        userRepository.save(newUser);
        return newUser;
    }

    public Optional<User> getUser(User user) {
        Optional<User> userFromDb = userRepository.findByLogin(user.getLogin());
//        if (userFromDb.isEmpty()) {
//            return User.builder()
//                    .build();
//        }
        return userFromDb;
    }
}
