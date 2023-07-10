package ru.home.proj.tasklist.controllers;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.home.proj.tasklist.dtos.UserDto;
import ru.home.proj.tasklist.entities.User;
import ru.home.proj.tasklist.mappers.TaskMapper;
import ru.home.proj.tasklist.mappers.UserMapper;
import ru.home.proj.tasklist.services.TaskService;
import ru.home.proj.tasklist.services.UserService;
import ru.home.proj.tasklist.utils.validations.OnUpdate;

@RestController
@RequestMapping("/user")
@Validated
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    private final UserMapper userMapper;

    @PutMapping
    public UserDto update(@Validated(OnUpdate.class) @RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.save(user);

        return userMapper.toDto(updatedUser);
    }

    @PostMapping
    public UserDto create(@Validated(OnUpdate.class) @RequestBody UserDto dto) {
        User user = userMapper.toEntity(dto);
        User updatedUser = userService.save(user);

        return userMapper.toDto(updatedUser);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id) {
        User user = userService.get(id);

        return userMapper.toDto(user);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        userService.deleteById(id);
    }


}
