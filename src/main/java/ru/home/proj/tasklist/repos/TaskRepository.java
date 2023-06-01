package ru.home.proj.tasklist.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.home.proj.tasklist.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

//    List<Task> findAllByUser
}
