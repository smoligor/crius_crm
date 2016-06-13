package com.becomejavasenior.template.impl;

import com.becomejavasenior.entity.Company;
import com.becomejavasenior.entity.Contact;
import com.becomejavasenior.entity.Deal;
import com.becomejavasenior.entity.Stage;
import com.becomejavasenior.template.DealDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class JdbcDealDAOImpl implements DealDAO {

    private static final String INSERT_DEAL_SQL = "INSERT INTO deal (stage_id, responsible_user_id, company_id, created_by_id, " +
            "name, amount, deleted, date_create, primary_contact_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String UPDATE_DEAL_SQL = "UPDATE deal SET stage_id = :stage_id, responsible_user_id = :responsible_user_id, " +
            "company_id = :company_id, created_by_id = :created_by_id, name = :name," +
            " amount = :amount, deleted = :deleted, date_create = :date_create, primary_contact_id = :primary_contact_id WHERE id = :id";
    private static final String SELECT_ALL_DEALS_SQL = "SELECT id, name, stage_id, responsible_user_id," +
            " amount, company_id, date_create, created_by_id, primary_contact_id FROM deal WHERE NOT deleted LIMIT 30";
    private static final String SELECT_DEAL_BY_ID_SQL = "SELECT id, name, stage_id, responsible_user_id," +
            " amount, company_id, date_create, created_by_id, primary_contact_id FROM deal WHERE NOT deleted AND id = ? LIMIT 30";
    private static final String DELETE_DEAL = "UPDATE deal SET deleted = TRUE WHERE id = ?";
    private static final String INSERT_DEAL_CONTACT_SQL = "INSERT INTO deal_contact (deal_id, contact_id) VALUES (?, ?)";
    private static final String SELECT_ALL_STAGE_DEALS_SQL = "SELECT id, name FROM stage_deals WHERE deleted = FALSE";
    private static final String SELECT_ALL_DEAL_BY_STAGE = "SELECT\n" +
            "  deal.id,\n" +
            "  deal.name,\n" +
            "  deal.amount,\n" +
            "  company.id AS companyId,\n" +
            "  company.name AS companyName\n" +
            "FROM deal\n" +
            "  JOIN company ON company.id = deal.company_id\n" +
            "WHERE deal.id IN (SELECT deal.id\n" +
            "                  FROM deal\n" +
            "                    JOIN stage_deals ON deal.stage_id = stage_deals.id WHERE stage_deals.name=?) LIMIT 30";
    private static final String SELECT_ALL_CONTACT_FOR_DEAL = "SELECT contact.id, contact.name FROM deal JOIN\n" +
            "deal_contact ON\n" +
            "deal.id=deal_contact.deal_id JOIN contact ON deal_contact.contact_id=contact.id\n" +
            "WHERE deal.id=?";

    private static final String SELECT_ALL_STAGE_DEALS = "SELECT id, name FROM stage_deals LIMIT 4";
    private static final String SELECT_MAIN_DATA_FOR_DEALS = "SELECT\n" +
            "  deal.name,\n" +
            "  deal.amount,\n" +
            "  stage_deals.name AS stage,\n" +
            "  contact.id AS contactId,\n" +
            "  contact.name AS contact,\n" +
            "  company.id AS companyId,\n" +
            "  company.name AS company\n" +
            "FROM deal\n" +
            "  JOIN stage_deals ON deal.stage_id = stage_deals.id\n" +
            "  JOIN contact ON deal.primary_contact_id = contact.id\n" +
            "  JOIN company ON contact.company_id = company.id\n LIMIT 30";

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);

    }

    @Override
    public List<Deal> getDealsForList() {

        return jdbcTemplate.query(SELECT_MAIN_DATA_FOR_DEALS, (resultSet, rowNum) -> {
            Deal deal = new Deal();
            Contact contact = new Contact();
            Company company = new Company();
            Stage stage = new Stage();

            deal.setName(resultSet.getString("name"));
            deal.setAmount(resultSet.getBigDecimal("amount"));
            stage.setName(resultSet.getString("stage"));
            deal.setStage(stage);
            contact.setId(resultSet.getInt("contactId"));
            contact.setName(resultSet.getString("contact"));
            company.setId(resultSet.getInt("companyId"));
            company.setName(resultSet.getString("company"));
            contact.setCompany(company);
            deal.setPrimaryContact(contact);
            return deal;
        });
    }

    @Override
    public List<Deal> getDealsByStage(String stage) {

        return jdbcTemplate.query(SELECT_ALL_DEAL_BY_STAGE, new Object[]{stage},
                (resultSet, rowNum) -> {
                    Deal deal = new Deal();
                    Company company = new Company();

                    deal.setId(resultSet.getInt("id"));
                    deal.setName(resultSet.getString("name"));
                    deal.setAmount(resultSet.getBigDecimal("amount"));
                    company.setId(resultSet.getInt("companyId"));
                    company.setName(resultSet.getString("companyName"));
                    deal.setCompany(company);
                    return deal;
                }
        );
    }

    @Override
    public List<Contact> getContactsByDealId(int dealId) {

        return jdbcTemplate.query(SELECT_ALL_CONTACT_FOR_DEAL, new Object[]{dealId}, (resultSet, rowNum) -> {
            Contact contact = new Contact();
            contact.setId(resultSet.getInt("id"));
            contact.setName(resultSet.getString("name"));
            return contact;
        });
    }

    @Override
    public List<Stage> getAllStage() {

        return jdbcTemplate.query(SELECT_ALL_STAGE_DEALS, (resultSet, rowNum) -> {
            Stage stage = new Stage();
            stage.setId(resultSet.getInt("id"));
            stage.setName(resultSet.getString("name"));
            return stage;
        });
    }

    @Override
    public int insert(Deal deal) {
        int key = jdbcTemplate.update(INSERT_DEAL_SQL, statement -> {
            statement.setInt(1, deal.getStage().getId());
            statement.setObject(2, deal.getResponsibleUser() == null ? null : deal.getResponsibleUser().getId());
            statement.setInt(3, deal.getCompany().getId());
            statement.setInt(4, deal.getCreator().getId());
            statement.setString(5, deal.getName());
            statement.setBigDecimal(6, deal.getAmount());
            statement.setBoolean(7, deal.isDelete());
            statement.setTimestamp(8, new java.sql.Timestamp(deal.getDateCreate() == null ? System.currentTimeMillis() : deal.getDateCreate().getTime()));
            statement.setObject(9, deal.getPrimaryContact() == null ? null : deal.getPrimaryContact().getId());
        });
        if (deal.getContacts() != null && !deal.getContacts().isEmpty()) {
            for (Contact contact : deal.getContacts()) {
                addContactToDeal(deal, contact);
            }
        }
        return key;
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_DEAL, id);
    }

    @Override
    public void update(Deal deal) {
        MapSqlParameterSource sqlParameterSource = new MapSqlParameterSource();
        sqlParameterSource.addValue("stage_id", deal.getStage().getId());
        sqlParameterSource.addValue("responsible_user_id", deal.getResponsibleUser() == null ? null : deal.getResponsibleUser().getId());
        sqlParameterSource.addValue("company_id", deal.getCompany().getId());
        sqlParameterSource.addValue("created_by_id", deal.getCreator().getId());
        sqlParameterSource.addValue("name", deal.getName());
        sqlParameterSource.addValue("amount", deal.getAmount());
        sqlParameterSource.addValue("deleted", deal.isDelete());
        sqlParameterSource.addValue("date_create", new java.sql.Timestamp(deal.getDateCreate().getTime()));
        sqlParameterSource.addValue("primary_contact_id", deal.getPrimaryContact() == null ? null : deal.getPrimaryContact().getId());
        sqlParameterSource.addValue("id", deal.getId());

        namedParameterJdbcTemplate.update(UPDATE_DEAL_SQL, sqlParameterSource);
    }

    @Override
    public List<Deal> getAll() {

        return jdbcTemplate.query(SELECT_ALL_DEALS_SQL, new DealRowMapper());
    }

    @Override
    public Deal getById(int id) {

        return jdbcTemplate.queryForObject(SELECT_DEAL_BY_ID_SQL, new Object[]{id}, new DealRowMapper());
    }


    @Override
    public void addContactToDeal(Deal deal, Contact contact) {
        if (deal != null && deal.getId() > 0 && contact != null && contact.getId() > 0) {
            jdbcTemplate.update(INSERT_DEAL_CONTACT_SQL, deal.getId(), contact.getId());
        }
    }

    @Override
    public Map<Integer, String> getStageDealsList() {

        return jdbcTemplate.query(SELECT_ALL_STAGE_DEALS_SQL, resultSet -> {
            Map<Integer, String> stages = new HashMap<>();
            while (resultSet.next()) {
                stages.put(resultSet.getInt("id"), resultSet.getString("name"));
            }
            return stages;
        });
    }
}
