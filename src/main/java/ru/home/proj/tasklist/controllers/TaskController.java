package ru.home.proj.tasklist.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.home.proj.tasklist.dtos.TaskDto;
import ru.home.proj.tasklist.entities.Task;
import ru.home.proj.tasklist.mappers.TaskMapper;
import ru.home.proj.tasklist.services.TaskService;
import ru.home.proj.tasklist.utils.validations.OnCreate;
import ru.home.proj.tasklist.utils.validations.OnUpdate;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/tasks")
@Validated
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @PutMapping
    @PreAuthorize("@securityExpression(#dto.id)")
    public TaskDto update(@Validated(OnUpdate.class) @RequestBody TaskDto dto) {
        Task task = taskMapper.toEntity(dto);
        Task updateTask = taskService.save(task);

        return taskMapper.toDto(updateTask);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TaskDto getById(@PathVariable Long id) {
        Task task = taskService.get(id);

        return taskMapper.toDto(task);
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteById(@PathVariable Long id) {
        taskService.deleteById(id);
    }

    @GetMapping("/{id}/tasks")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<TaskDto> getAllTasksByUserId(@PathVariable Long id) {
        List<Task> taskList = taskService.findAllByUserId(id);

        return taskList.stream().map(taskMapper::toDto).collect(Collectors.toList());
    }

    @PostMapping("/{userId}/tasks")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public TaskDto createTask(@PathVariable Long userId, @Validated(OnCreate.class) TaskDto dto) {

        Task task = taskMapper.toEntity(dto);
        Task createdTask = taskService.saveWithUserId(task, userId);

        return taskMapper.toDto(createdTask);
    }
}
