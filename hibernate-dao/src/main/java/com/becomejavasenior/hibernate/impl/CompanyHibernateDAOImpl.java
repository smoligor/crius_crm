package com.becomejavasenior.hibernate.impl;

import com.becomejavasenior.entity.Company;
import com.becomejavasenior.entity.File;
import com.becomejavasenior.entity.Tag;
import com.becomejavasenior.hibernate.CompanyHibernateDAO;
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
public class CompanyHibernateDAOImpl implements CompanyHibernateDAO {
    private final String INSERT_COMPANY_TAG_SQL = "INSERT INTO contact_company_tag (tag_id, company_id) VALUES (?, ?)";
    private static final String DELETE_SQL = "update Company as company set company.delete = true where company.id = :id";
    @Autowired
    private SessionFactory sessionFactory;
    @Override
    public void companyTag(Company company, Tag tag) {
        Query query = sessionFactory.getCurrentSession().createQuery(INSERT_COMPANY_TAG_SQL);
       query.setInteger("1", company.getId());
        query.setInteger("2", tag.getId());
        query.executeUpdate();
    }

    @Override
    public int insert(Company company) {
        sessionFactory.getCurrentSession().save(company);
        return company.getId();
    }

    @Override
    public void update(Company company) {
        sessionFactory.getCurrentSession().update(company);

    }

    @Override
    public List<Company> getAll() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Company.class)
                .add(Restrictions.eq("delete", false));
        return (List<Company>) criteria.list();
    }

    @Override
    public Company getById(int id) {
        Company company = (Company) sessionFactory.getCurrentSession().get(Company.class, id);
        return company.isDelete()? null: company;
    }

    @Override
    public void delete(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery(DELETE_SQL);
        query.setParameter("id", id);
        query.executeUpdate();

    }
}
