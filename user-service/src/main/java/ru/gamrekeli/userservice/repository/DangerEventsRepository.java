package ru.gamrekeli.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gamrekeli.userservice.model.DangerEvents;

@Repository
public interface DangerEventsRepository extends JpaRepository<DangerEvents, Long> {
}
