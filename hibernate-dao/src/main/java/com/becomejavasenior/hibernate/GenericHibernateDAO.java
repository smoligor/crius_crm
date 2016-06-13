package com.becomejavasenior.hibernate;

import java.util.List;

public interface GenericHibernateDAO<T> {
    int insert(T t);
    void update(T t);
    List<T> getAll();
    T getById(int id);
    void delete(int id);
}
