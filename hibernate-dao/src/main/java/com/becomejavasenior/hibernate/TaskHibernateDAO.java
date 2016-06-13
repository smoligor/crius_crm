package com.becomejavasenior.hibernate;

import com.becomejavasenior.entity.Task;

import java.util.List;
import java.util.Map;

public interface TaskHibernateDAO extends GenericHibernateDAO<Task> {

    List<String> getAllTaskStatus();

    List<String> getAllTaskType();

    Map<Integer, String> getTaskTypeList();
}
