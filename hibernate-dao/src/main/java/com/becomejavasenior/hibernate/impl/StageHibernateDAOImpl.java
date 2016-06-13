package com.becomejavasenior.hibernate.impl;

import com.becomejavasenior.entity.Stage;
import com.becomejavasenior.hibernate.StageHibernateDAO;
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
public class StageHibernateDAOImpl implements StageHibernateDAO {
    @Autowired
    private SessionFactory sessionFactory;
    private static final String DELETE_SQL = "update Stage as stage set stage.delete = true where stage.id = :id";
    @Override
    public int insert(Stage stage) {
        sessionFactory.getCurrentSession().save(stage);
        return stage.getId();
    }

    @Override
    public void update(Stage stage) {
        sessionFactory.getCurrentSession().update(stage);

    }

    @Override
    public List<Stage> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Stage.class)
                .add(Restrictions.eq("delete", false));
        return (List<Stage>) criteria.list();
    }

    @Override
    public Stage getById(int id) {
        Stage stage  = (Stage) sessionFactory.getCurrentSession().get(Stage.class, id);
        return stage.isDelete()? null: stage;
    }

    @Override
    public void delete(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_SQL);
        query.setParameter("id", id);
        query.executeUpdate();

    }
}
