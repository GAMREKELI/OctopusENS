package ru.gamrekeli.userservice.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.gamrekeli.userservice.model.FriendShip;

import java.util.List;

@Repository
public interface FriendShipRepository extends JpaRepository<FriendShip, Long> {

    @Query("SELECT f FROM FriendShip f WHERE f.user.userId = :userId AND f.status = NO")
    List<FriendShip> findAllSubscribersByUserId(@Param("userId") Long userId);

    @Query("SELECT f FROM FriendShip f WHERE (f.user.userId = :userId OR f.friend.userId = :userId) AND f.status = YES")
    List<FriendShip> findAllFriendsByUserId(@Param("userId") Long userId);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("UPDATE FriendShip f SET f.status = YES WHERE f.user.userId = :userId AND f.friend.userId = :friendId")
    void confirmationAddFriend(@Param("userId") Long userId, @Param("friendId") Long friendId) ;
}
