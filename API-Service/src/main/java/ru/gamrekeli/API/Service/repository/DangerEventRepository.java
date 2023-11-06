package ru.gamrekeli.API.Service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.gamrekeli.API.Service.model.DangerEvent;

@Repository
public interface DangerEventRepository extends JpaRepository<DangerEvent, Long> {
}
