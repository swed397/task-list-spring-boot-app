package ru.home.proj.tasklist.mappers;

import org.mapstruct.Mapper;
import ru.home.proj.tasklist.dtos.UserDto;
import ru.home.proj.tasklist.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);

    User toEntity(UserDto dto);
}
