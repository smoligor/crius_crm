package com.becomejavasenior.template.impl;

import com.becomejavasenior.entity.Company;
import com.becomejavasenior.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyMapper implements RowMapper<Company> {
    @Override
    public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Company company = new Company();
        User responsibleUser = new User();
        User creator = new User();
        company.setId(resultSet.getInt("id"));
        company.setName(resultSet.getString("name"));
        company.setPhone(resultSet.getString("phone"));
        company.setEmail(resultSet.getString("email"));
        company.setAddress(resultSet.getString("address"));
        company.setResponsibleUser(responsibleUser);
        responsibleUser.setId(resultSet.getInt("responsible_user_id"));
        company.setWeb(resultSet.getString("web"));
        company.setDelete(false);
        company.setCreator(creator);
        creator.setId(resultSet.getInt("created_by_id"));
        company.setDateCreate(resultSet.getTimestamp("date_create"));

        return company;
    }
}
