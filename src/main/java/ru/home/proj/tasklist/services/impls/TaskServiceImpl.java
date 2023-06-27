package ru.home.proj.tasklist.services.impls;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.proj.tasklist.entities.Task;
import ru.home.proj.tasklist.enums.StatusEnum;
import ru.home.proj.tasklist.exceptions.ResourceNotFoundExcept;
import ru.home.proj.tasklist.repos.TaskRepository;
import ru.home.proj.tasklist.services.StatusService;
import ru.home.proj.tasklist.services.TaskService;
import ru.home.proj.tasklist.services.UserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    @Lazy
    private UserService userService;
    private final TaskRepository taskRepository;
    private final StatusService statusService;

    @Override
    @Transactional
    public Task save(Task entity) {
        if (entity.getStatus() == null) {
            entity.setStatus(statusService.findStatusByName(StatusEnum.TO_DO.getName()));
        }

        return taskRepository.save(entity);
    }

    @Override
    @Transactional
    public void delete(Task entity) {
        taskRepository.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Task get(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new ResourceNotFoundExcept("User not found"));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Task> findAllByUserId(Long userId) {
        return taskRepository.findAllByUserId(userId);
    }

    @Override
    @Transactional
    public Task saveWithUserId(Task task, Long userId) {
        if (task.getStatus() == null) {
            task.setStatus(statusService.findStatusByName(StatusEnum.TO_DO.getName()));
        }

        task.getUserSet().add(userService.get(userId));

        return taskRepository.save(task);
    }
}
