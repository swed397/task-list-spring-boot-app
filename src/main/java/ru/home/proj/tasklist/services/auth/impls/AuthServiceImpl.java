package ru.home.proj.tasklist.services.auth.impls;

import org.springframework.stereotype.Service;
import ru.home.proj.tasklist.dtos.auth.JwtRequest;
import ru.home.proj.tasklist.dtos.auth.JwtResponse;
import ru.home.proj.tasklist.services.auth.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    @Override
    public JwtResponse login(JwtRequest loginRequest) {
        return null;
    }

    @Override
    public JwtResponse refresh(String refreshToken) {
        return null;
    }
}
