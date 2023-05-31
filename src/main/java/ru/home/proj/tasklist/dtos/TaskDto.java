package ru.home.proj.tasklist.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import ru.home.proj.tasklist.entities.Status;
import ru.home.proj.tasklist.utils.validations.OnCreate;
import ru.home.proj.tasklist.utils.validations.OnUpdate;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto {

    @NotNull(message = "Id must be not null", groups = OnUpdate.class)
    private Long id;

    @NotBlank(message = "Title must be not null", groups = {OnCreate.class, OnUpdate.class})
    private Long title;
    private String description;
    private Status status;

    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    @JsonFormat(pattern = "yyy-MM-dd HH:mm")
    private LocalDateTime expirationDate;
}
