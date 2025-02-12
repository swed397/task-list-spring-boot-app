package ru.home.proj.tasklist.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.proj.tasklist.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

    Status findByName(String name);
}
