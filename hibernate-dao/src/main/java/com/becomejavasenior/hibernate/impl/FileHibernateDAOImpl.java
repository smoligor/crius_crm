package com.becomejavasenior.hibernate.impl;

import com.becomejavasenior.entity.File;
import com.becomejavasenior.hibernate.FileHibernateDAO;
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
public class FileHibernateDAOImpl implements FileHibernateDAO {
    private static final String DELETE_SQL = "update File as file set file.delete = true where file.id = :id";
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public int insert(File file) {
        sessionFactory.getCurrentSession().save(file);
        return file.getId();
    }

    @Override
    public void update(File file) {
        sessionFactory.getCurrentSession().update(file);

    }

    @Override
    public List<File> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(File.class)
                .add(Restrictions.eq("delete", false));
        return (List<File>) criteria.list();
    }

    @Override
    public File getById(int id) {
        File file = (File) sessionFactory.getCurrentSession().get(File.class, id);
        return file.isDelete()? null: file;
    }

    @Override
    public void delete(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_SQL);
        query.setParameter("id", id);
        query.executeUpdate();

    }
}
