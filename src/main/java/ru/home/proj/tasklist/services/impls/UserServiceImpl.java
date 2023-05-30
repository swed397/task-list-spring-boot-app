package ru.home.proj.tasklist.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.home.proj.tasklist.entities.User;
import ru.home.proj.tasklist.repos.UserRepository;
import ru.home.proj.tasklist.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    public User get(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
