package ru.home.proj.tasklist.services;

import ru.home.proj.tasklist.entities.User;
import ru.home.proj.tasklist.utils.Crud;

public interface UserService extends Crud<User> {

    void deleteById(Long id);

    User findByUserName(String username);

    User registerNewUser(User user);
}
