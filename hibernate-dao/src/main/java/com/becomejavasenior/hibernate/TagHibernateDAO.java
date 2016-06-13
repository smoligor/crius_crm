package com.becomejavasenior.hibernate;

import com.becomejavasenior.entity.Tag;

public interface TagHibernateDAO extends GenericHibernateDAO<Tag> {
    int insertForCompanyContact(Tag tag, Object object);
}
