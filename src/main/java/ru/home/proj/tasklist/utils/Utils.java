package ru.home.proj.tasklist.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import ru.home.proj.tasklist.config.security.JwtEntity;

public class Utils {

    public static JwtEntity getPrincipal() {
        return (JwtEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
