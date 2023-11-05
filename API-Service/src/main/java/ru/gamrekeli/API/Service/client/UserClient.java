package ru.gamrekeli.API.Service.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import ru.gamrekeli.API.Service.model.User;

import java.util.List;

@HttpExchange
public interface UserClient {

    @GetExchange("/api/v1/user/" + "/{userId}")
    public User showUser(@PathVariable("userId") Long userId);

    @GetExchange("/api/v1/user/friends/" + "/{userId}")
    public List<User> showFriends(@PathVariable("userId") Long userId);

    @GetExchange("/api/v1/user/subscribers" + "/{userId}")
    public List<User> showSubscribers(@PathVariable("userId") Long userId);
}
