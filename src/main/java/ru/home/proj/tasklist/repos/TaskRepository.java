package ru.home.proj.tasklist.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.home.proj.tasklist.entities.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query(
            value = "select * from tasks t join users_tasks ut on t.id = ut.user_id where ut.user_id = :#{#userId}",
            nativeQuery = true
    )
    List<Task> findAllByUserId(@Param("userId") Long userId);
}
