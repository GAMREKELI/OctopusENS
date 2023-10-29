package ru.gamrekeli.API.Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gamrekeli.API.Service.model.FriendShip;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendShipRepository extends JpaRepository<FriendShip, Long> {

    @Query("SELECT f FROM FriendShip f WHERE f.user = :userId AND f.status = NO")
    List<FriendShip> findAllSubscribersByUserId(@Param("userId") Long userId);

    @Query("SELECT f FROM FriendShip f WHERE f.user = :userId AND f.status = YES")
    List<FriendShip> findAllFriendsByUserId(@Param("userId") Long userId);
}
