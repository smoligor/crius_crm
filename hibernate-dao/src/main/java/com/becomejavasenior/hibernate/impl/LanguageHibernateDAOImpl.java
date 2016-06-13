package com.becomejavasenior.hibernate.impl;

import com.becomejavasenior.entity.Language;
import com.becomejavasenior.hibernate.LanguageHibernateDAO;
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
public class LanguageHibernateDAOImpl implements LanguageHibernateDAO {

    @Autowired
    private SessionFactory sessionFactory;
    private static final String DELETE_SQL = "update Language as language set language.delete = true" +
            " where language.id = :id";


    @Override
    public int insert(Language language) {
        sessionFactory.getCurrentSession().save(language);
        return language.getId();
    }

    @Override
    public void update(Language language) {
        sessionFactory.getCurrentSession().update(language);
    }

    @Override
    public List<Language> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Language.class)
                .add(Restrictions.eq("delete", false));
        return (List<Language>) criteria.list();
    }

    @Override
    public Language getById(int id) {
        Language language = (Language) sessionFactory.getCurrentSession().get(Language.class, id);
        return language.isDelete()? null: language;
    }

    @Override
    public void delete(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_SQL);
        query.setParameter("id", id);
        query.executeUpdate();

    }
}
