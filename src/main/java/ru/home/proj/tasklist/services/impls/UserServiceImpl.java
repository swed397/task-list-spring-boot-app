package ru.home.proj.tasklist.services.impls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Override
    @Caching(put = {
            @CachePut(value = "UserService::get", key = "#entity.id"),
            @CachePut(value = "UserService::delete", key = "#entity.id")
    })
    @Transactional
    public User save(User entity) {
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    @Override
    @CacheEvict(value = "UserService::get", key = "#entity.id")
    @Transactional
    public void delete(User entity) {
        userRepository.delete(entity);
    }

    @Override
    @Cacheable(value = "UserService::get", key = "#id")
    @Transactional(readOnly = true)
    public User get(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("No such user "));
    }

    @Override
    @CacheEvict(value = "UserService::get", key = "#id")
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "UserService::findByUserName", key = "#username")
    @Transactional(readOnly = true)
    public User findByUserName(String username) {
        log.info("Finding user by username: " + username);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("No such user "));
    }

    @Override
    @Caching(cacheable = {
            @Cacheable(value = "UserService::get", key = "#user.id"),
            @Cacheable(value = "UserService::delete", key = "#user.id")
    })
    @Transactional
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
    @Cacheable(value = "UserService::isTaskOwner", key = "#id + '.' + #taskId")
    @Transactional(readOnly = true)
    public Boolean isTaskOwner(Long id, Long taskId) {
        return get(id).getId().equals(taskService.get(taskId).getUserSet().stream()
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No such user find"))
                .getId());
    }
}
