package com.becomejavasenior.template.impl;

import com.becomejavasenior.entity.*;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DealRowMapper implements RowMapper<Deal> {
    @Override
    public Deal mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Deal deal = new Deal();
        User responsibleUser = new User();
        User creator = new User();
        Company company = new Company();
        Stage stage = new Stage();

        deal.setId(resultSet.getInt("id"));
        responsibleUser.setId(resultSet.getInt("responsible_user_id"));
        deal.setResponsibleUser(responsibleUser);
        company.setId(resultSet.getInt("company_id"));
        deal.setCompany(company);
        creator.setId(resultSet.getInt("created_by_id"));
        deal.setCreator(creator);
        deal.setName(resultSet.getString("name"));
        deal.setAmount(resultSet.getBigDecimal("amount"));
        deal.setDelete(false);
        deal.setDateCreate(resultSet.getTimestamp("date_create"));
        stage.setId(resultSet.getInt("stage_id"));
        deal.setStage(stage);
        if (resultSet.getObject("primary_contact_id") != null) {
            Contact primaryContact = new Contact();
            primaryContact.setId(resultSet.getInt("primary_contact_id"));
            deal.setPrimaryContact(primaryContact);
        }
        return deal;
    }
}
