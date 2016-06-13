package com.becomejavasenior.hibernate.impl;

import com.becomejavasenior.entity.Contact;
import com.becomejavasenior.entity.File;
import com.becomejavasenior.hibernate.ContactHibernateDAO;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class ContactHibernateDAOImpl implements ContactHibernateDAO {
    private static final String DELETE_SQL = "update Contact as contact set contact.delete = true where contact.id = :id";
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public int insert(Contact contact) {
        sessionFactory.getCurrentSession().save(contact);
        return contact.getId();
    }

    @Override
    public void update(Contact contact) {
        sessionFactory.getCurrentSession().update(contact);

    }

    @Override
    public List<Contact> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Contact.class)
                .add(Restrictions.eq("delete", false));
        return (List<Contact>) criteria.list();
    }

    @Override
    public Contact getById(int id) {
        Contact contact = (Contact) sessionFactory.getCurrentSession().get(Contact.class, id);
        return contact.isDelete()? null: contact;
    }

    @Override
    public void delete(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_SQL);
        query.setParameter("id", id);
        query.executeUpdate();

    }
}
