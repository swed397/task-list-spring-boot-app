package ru.home.proj.tasklist.services.impls;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.home.proj.tasklist.entities.Task;
import ru.home.proj.tasklist.repos.TaskRepository;
import ru.home.proj.tasklist.services.TaskService;
import ru.home.proj.tasklist.services.UserService;

import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Override
    public Task save(Task entity) {
        return taskRepository.save(entity);
    }

    @Override
    public void delete(Task entity) {
        taskRepository.delete(entity);
    }

    @Override
    public Task get(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public List<Task> findAllByUserId(Long id) {
        return List.of();
    }

    @Override
    public Task saveWithUserId(Task task, Long userId) {
        task.setUserSet(Set.of(userService.get(userId)));

        return taskRepository.save(task);
    }
}
