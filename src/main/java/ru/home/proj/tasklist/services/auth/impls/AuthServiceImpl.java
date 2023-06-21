package ru.home.proj.tasklist.services.auth.impls;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.home.proj.tasklist.config.security.JwtTokenProvider;
import ru.home.proj.tasklist.dtos.auth.JwtRequest;
import ru.home.proj.tasklist.dtos.auth.JwtResponse;
import ru.home.proj.tasklist.entities.User;
import ru.home.proj.tasklist.exceptions.AccessDeniedExcept;
import ru.home.proj.tasklist.services.UserService;
import ru.home.proj.tasklist.services.auth.AuthService;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        JwtResponse jwtResponse = new JwtResponse();

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));

        User user = userService.findByUserName(loginRequest.getUsername());

        jwtResponse.setId(user.getId());
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(user.getId(), user.getUsername(),
                user.getRolesSet()));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(user.getId(), user.getUsername()));

        return jwtResponse;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        JwtResponse jwtResponse = new JwtResponse();

        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new AccessDeniedExcept("No access");
        }

        Long userId = Long.valueOf(jwtTokenProvider.getIdFromToken(refreshToken));
        User user = userService.get(userId);

        jwtResponse.setId(userId);
        jwtResponse.setUsername(user.getUsername());
        jwtResponse.setAccessToken(jwtTokenProvider.createAccessToken(userId, user.getUsername(), user.getRolesSet()));
        jwtResponse.setRefreshToken(jwtTokenProvider.createRefreshToken(userId, user.getUsername()));

        return jwtResponse;
    }
}
