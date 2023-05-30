package ru.home.proj.tasklist.utils;

public interface Crud<T> {

    T save(T entity);

    void delete(T entity);

    T get(Long id);
 }
