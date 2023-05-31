package ru.home.proj.tasklist.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.home.proj.tasklist.utils.validations.OnCreate;
import ru.home.proj.tasklist.utils.validations.OnUpdate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = "Name must be not null", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotBlank(message = "Username must be not null", groups = {OnCreate.class, OnUpdate.class})
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password must be not null", groups = {OnCreate.class, OnUpdate.class})
    private String password;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Confirm must be not null", groups = {OnCreate.class})
    private String passwordConfirm;
}
