package ru.home.proj.tasklist.services;

import ru.home.proj.tasklist.entities.Status;

public interface StatusService {

    Status findStatusByName(String name);
}
