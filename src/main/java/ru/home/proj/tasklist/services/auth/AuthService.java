package ru.home.proj.tasklist.services.auth;

import ru.home.proj.tasklist.dtos.auth.JwtRequest;
import ru.home.proj.tasklist.dtos.auth.JwtResponse;

public interface AuthService {

    JwtResponse login(JwtRequest loginRequest);

    JwtResponse refresh(String refreshToken);
}
