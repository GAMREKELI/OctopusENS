package ru.gamrekeli.API.Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gamrekeli.API.Service.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
