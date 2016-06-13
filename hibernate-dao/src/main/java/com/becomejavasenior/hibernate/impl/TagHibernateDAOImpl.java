package com.becomejavasenior.hibernate.impl;

import com.becomejavasenior.hibernate.TagHibernateDAO;
import com.becomejavasenior.entity.Tag;
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
public class TagHibernateDAOImpl implements TagHibernateDAO {

    private static final String DELETE_SQL = "update Tag as tag set tag.delete = true where tag.id = :id";

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int insertForCompanyContact(Tag tag, Object object) {
        return 0;
    }

    @Override
    public int insert(Tag tag) {
        sessionFactory.getCurrentSession().save(tag);
        return tag.getId();
    }

    @Override
    public void update(Tag tag) {
        sessionFactory.getCurrentSession().update(tag);
    }

    @Override
    /**
     * Using Criteria API
     */
    public List<Tag> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Tag.class)
                .add(Restrictions.eq("delete", false));
        return (List<Tag>) criteria.list();
    }

    @Override
    public Tag getById(int id) {
        Tag tag = (Tag) sessionFactory.getCurrentSession().get(Tag.class, id);
        return tag.isDelete()? null: tag;
    }

    @Override
    public void delete(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_SQL);
        query.setParameter("id", id);
        query.executeUpdate();
    }
}
