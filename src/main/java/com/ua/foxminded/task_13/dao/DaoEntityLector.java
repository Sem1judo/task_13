package com.ua.foxminded.task_13.dao;

import com.ua.foxminded.task_13.model.Lector;

import java.time.LocalDateTime;

public interface DaoEntityLector extends DaoEntity<Lector> {

     int getLessonsByTime(LocalDateTime start, LocalDateTime end);
}

