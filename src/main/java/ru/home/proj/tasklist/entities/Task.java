package ru.home.proj.tasklist.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Task {

    private Long id;
    private Long title;
    private String description;
    private Status status;
    private LocalDateTime expirationDate;
}
