package ru.home.proj.tasklist.entities;

import jakarta.persistence.Transient;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class User {

    private Long id;
    private String name;
    private String userName;
    private String password;

    @Transient
    private String passwordConfirm;
    private Set<Role> rolesSet;
    private List<Task> taskList;
}
