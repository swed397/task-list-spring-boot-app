package ru.home.proj.tasklist.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class JwtRequest {

    @NotBlank(message = "Username must be not null")
    private String userName;

    @NotBlank(message = "Password must be not null")
    private String password;
}
