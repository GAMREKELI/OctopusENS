package ru.gamrekeli.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gamrekeli.userservice.entity.User;
import ru.gamrekeli.userservice.repository.UserRepository;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
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

    @Override
    public Optional<User> getUser(User user) {
        Optional<User> userFromDb = userRepository.findByLogin(user.getLogin());
//        if (userFromDb.isEmpty()) {
//            return User.builder()
//                    .build();
//        }
        return userFromDb;
    }

    @Override
    public Long getUserId(String login) {
        Optional<User> user = userRepository.findByLogin(login);
        return user.get().getUserId();
    }
}
