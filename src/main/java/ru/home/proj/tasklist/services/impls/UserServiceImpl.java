package ru.home.proj.tasklist.services.impls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.home.proj.tasklist.entities.User;
import ru.home.proj.tasklist.enums.RolesEnum;
import ru.home.proj.tasklist.repos.UserRepository;
import ru.home.proj.tasklist.services.RoleService;
import ru.home.proj.tasklist.services.TaskService;
import ru.home.proj.tasklist.services.UserService;

import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;
    private final TaskService taskService;

    @Transactional
    @Override
    public User save(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Transactional
    @Override
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Transactional(readOnly = true)
    @Override
    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("No such user "));
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User findByUserName(String username) {
        log.info("Finding user by username: " + username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user "));
    }

    @Transactional
    @Override
    public User registerNewUser(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new IllegalStateException("User already created");
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())) {
            throw new IllegalStateException("Password and password confirm don't match");
        }

        user.setRolesSet(Set.of(roleService.findByName(RolesEnum.USER.name())));
        userRepository.save(user);

        return user;
    }

    @Override
    @Transactional(readOnly = true)
    public Boolean isTaskOwner(Long id, Long taskId) {
        //ToDo Refactor
        return get(id).getId().equals(taskService.get(taskId).getUserSet().stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No such user find"))
                .getId());
    }
}
