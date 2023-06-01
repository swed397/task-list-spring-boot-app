package ru.home.proj.tasklist.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.proj.tasklist.entities.Task;
import ru.home.proj.tasklist.repos.TaskRepository;
import ru.home.proj.tasklist.services.TaskService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;


    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

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
}
