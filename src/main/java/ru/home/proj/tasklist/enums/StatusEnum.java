package ru.home.proj.tasklist.enums;

import lombok.Getter;

@Getter
public enum StatusEnum {
    TO_DO("Сделать"),
    IN_PROGRESS("В работе"),
    DONE("Выполнена");

    private String name;

    StatusEnum(String name) {
        this.name = name;
    }
}
