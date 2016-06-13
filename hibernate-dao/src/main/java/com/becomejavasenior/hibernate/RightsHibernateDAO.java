package com.becomejavasenior.hibernate;

import com.becomejavasenior.entity.Rights;

import java.util.List;

public interface RightsHibernateDAO extends GenericHibernateDAO<Rights> {
    List<Rights> getRightsByUserId(int userId);
}
