package com.becomejavasenior.hibernate;

import com.becomejavasenior.entity.User;

public interface UserHibernateDAO extends GenericHibernateDAO<User> {
    String getUserLanguageCode(User user);
}
