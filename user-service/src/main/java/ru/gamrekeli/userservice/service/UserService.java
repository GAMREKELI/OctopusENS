package ru.gamrekeli.userservice.service;

import ru.gamrekeli.userservice.entity.User;
import java.util.Optional;

public interface UserService {
    User save(User user);
    Optional<User> getUser(User user);
}
