package com.becomejavasenior.hibernate.impl;
import com.becomejavasenior.entity.Rights;
import com.becomejavasenior.hibernate.RightsHibernateDAO;
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
public class RightsHibernateDAOImpl implements RightsHibernateDAO {
    @Autowired
    private SessionFactory sessionFactory;
    private static final String DELETE_SQL = "update Rights as rights set rights.delete = true where rights.id = :id";


    public List<Rights> getRightsByUserId(int userId) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Rights.class)
                .add(Restrictions.eq("userId", userId));
        return (List<Rights>) criteria.list();
    }


    public int insert(Rights rights) {
        sessionFactory.getCurrentSession().save(rights);
        return rights.getId();
    }


    public void update(Rights rights) {
        sessionFactory.getCurrentSession().update(rights);
    }


    public List<Rights> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Rights.class)
                .add(Restrictions.eq("delete", false));
        return (List<Rights>) criteria.list();
    }


    public Rights getById(int id) {
        Rights rights = (Rights) sessionFactory.getCurrentSession().get(Rights.class, id);
        return rights.isDelete()? null: rights;
    }

    @Override
    public void delete(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_SQL);
        query.setParameter("id", id);
        query.executeUpdate();
    }

}