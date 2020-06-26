package com.ua.foxminded.task_13.dao;

import com.ua.foxminded.task_13.model.Group;

public interface DaoEntityGroup extends DaoEntity<Group> {
     int getLessonsById(Long id);
}
