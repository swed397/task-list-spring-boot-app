package ru.home.proj.tasklist.config.security.expressions;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.home.proj.tasklist.config.security.JwtEntity;
import ru.home.proj.tasklist.enums.RolesEnum;
import ru.home.proj.tasklist.services.UserService;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class SecurityExpression {

    private final UserService userService;

    public boolean canAccessUser(Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        return userId.equals(id) || hasAnyRole(authentication, RolesEnum.ADMIN.name());
    }

    private boolean hasAnyRole(Authentication authentication, String... roleNames) {

        var userAuth = authentication.getAuthorities();
        var checkedRoles = Arrays.stream(roleNames).map(SimpleGrantedAuthority::new).toList();

        for (SimpleGrantedAuthority role : checkedRoles) {
            return userAuth.contains(role);
        }

        return false;
    }

    public boolean canAccessTask(Long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        JwtEntity user = (JwtEntity) authentication.getPrincipal();
        Long userId = user.getId();

        return userService.isTaskOwner(userId, taskId);
    }
}