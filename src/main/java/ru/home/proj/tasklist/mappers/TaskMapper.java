package ru.home.proj.tasklist.mappers;

import org.mapstruct.Mapper;
import ru.home.proj.tasklist.dtos.TaskDto;
import ru.home.proj.tasklist.entities.Task;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDto toDto(Task task);

    Task toEntity(TaskDto dto);
}
