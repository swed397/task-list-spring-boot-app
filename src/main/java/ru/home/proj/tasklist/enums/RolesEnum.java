package ru.home.proj.tasklist.enums;

public enum RolesEnum {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String name;

    RolesEnum(String name) {
        this.name = name;
    }
}
