package com.becomejavasenior.template;

import com.becomejavasenior.entity.Company;
import com.becomejavasenior.entity.Tag;

public interface CompanyDAO extends GenericDAO<Company> {
    void insertTagCompany(Company company, Tag tag);
}
