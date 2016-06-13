package com.becomejavasenior.hibernate;

import com.becomejavasenior.entity.Company;
import com.becomejavasenior.entity.Tag;

public interface CompanyHibernateDAO extends GenericHibernateDAO<Company> {
    void companyTag(Company company, Tag tag);
}
