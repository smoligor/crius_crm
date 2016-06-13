package com.becomejavasenior.hibernate.impl;

import com.becomejavasenior.entity.Currency;
import com.becomejavasenior.hibernate.CurrencyHibernateDAO;
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
public class CurrencyHibernateDAOImpl implements CurrencyHibernateDAO {
    private static final String DELETE_SQL = "update Currency as currency set currency.delete = true" +
            " where currency.id = :id";
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public Currency getActiveCurrency() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Currency.class)
                .add(Restrictions.eq("active", true));
        Currency currency = (Currency) criteria.list().get(0);
        return currency;
    }

    @Override
    public int insert(Currency currency) {
        sessionFactory.getCurrentSession().save(currency);
        return currency.getId();
    }

    @Override
    public void update(Currency currency) {
        sessionFactory.getCurrentSession().update(currency);

    }

    @Override
    public List<Currency> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Currency.class)
                .add(Restrictions.eq("delete", false));
        return (List<Currency>) criteria.list();
    }

    @Override
    public Currency getById(int id) {
        Currency currency = (Currency) sessionFactory.getCurrentSession().get(Currency.class, id);
        return currency.isDelete()? null: currency;
    }

    @Override
    public void delete(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_SQL);
        query.setParameter("id", id);
        query.executeUpdate();

    }
}
