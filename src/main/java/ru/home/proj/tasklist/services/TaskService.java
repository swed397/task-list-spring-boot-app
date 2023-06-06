package ru.home.proj.tasklist.services;

import ru.home.proj.tasklist.entities.Task;
import ru.home.proj.tasklist.utils.Crud;

import java.util.List;

public interface TaskService extends Crud<Task> {

    void deleteById(Long id);

    List<Task> findAllByUserId(Long id);

    Task saveWithUserId(Task task, Long userId);
}
