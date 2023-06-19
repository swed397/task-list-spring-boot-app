package ru.home.proj.tasklist.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.home.proj.tasklist.config.security.JwtEntity;
import ru.home.proj.tasklist.config.security.JwtTokenProvider;
import ru.home.proj.tasklist.dtos.UserDto;
import ru.home.proj.tasklist.dtos.auth.JwtRequest;
import ru.home.proj.tasklist.dtos.auth.JwtResponse;
import ru.home.proj.tasklist.entities.User;
import ru.home.proj.tasklist.mappers.UserMapper;
import ru.home.proj.tasklist.services.UserService;
import ru.home.proj.tasklist.services.auth.AuthService;
import ru.home.proj.tasklist.utils.validations.OnCreate;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Validated
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final UserService userService;
    private final UserMapper userMapper;
    private final JwtTokenProvider jwtTokenProvider;


    @PostMapping("/login")
    public JwtResponse login(@Validated @RequestBody JwtRequest jwtRequest) {
        log.info("Logging with " + jwtRequest.getUsername());

        return authService.login(jwtRequest);
    }

    @PostMapping("/register")
    public UserDto register(@Validated(OnCreate.class) @RequestBody UserDto userDto) {
        log.info("Registering with " + userDto.getUsername());

        User user = userMapper.toEntity(userDto);
        User createdUser = userService.save(user);

        return userMapper.toDto(createdUser);
    }

    @PostMapping("/refresh")
    public JwtResponse refresh(@RequestBody String refreshToken) {
        log.info("Refreshing token " + jwtTokenProvider.getUserNameFromToken(refreshToken));

        return authService.refresh(refreshToken);
    }

}
