package ru.home.proj.tasklist.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.home.proj.tasklist.entities.User;
import ru.home.proj.tasklist.exceptions.AccessDeniedExcept;
import ru.home.proj.tasklist.services.UserService;

@Service
@RequiredArgsConstructor
public class JwtUserDetailService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUserName(username);
        if(user == null) {
            throw new AccessDeniedExcept("No such username");
        }

        return JwtEntityFactory.create(user);
    }
}
