package com.becomejavasenior.template.impl;

import com.becomejavasenior.entity.Company;
import com.becomejavasenior.entity.Tag;
import com.becomejavasenior.template.CompanyDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class CompanyDAOImpl implements CompanyDAO {
    private static final String INSERT_SQL = "INSERT INTO company (name, phone, email, address, responsible_user_id," +
            " web, deleted, created_by_id, date_create) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_SQL = "UPDATE company SET name = :name, phone = :phone, email = :email, address = :address," +
            " responsible_user_id = :responsible_user_id, web = :web, deleted = :deleted, created_by_id = :created_by_id, date_create = :date_create WHERE id = :id";
    private static final String SELECT_BY_ID_SQL = "SELECT id, name, phone, email, address, responsible_user_id, web," +
            " created_by_id, date_create\nFROM company WHERE NOT deleted AND id = ?";
    private static final String SELECT_ALL_SQL = "SELECT id, name, phone, email, address, responsible_user_id, web," +
            " created_by_id, date_create\nFROM company WHERE NOT deleted";
    private static final String DELETE_SQL = "UPDATE company SET deleted = TRUE WHERE id = ?";
    private final String INSERT_COMPANY_TAG_SQL = "INSERT INTO contact_company_tag (tag_id, company_id) VALUES (?, ?)";


    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    /**
     * used JdbcTemplate
     */
    @Override
    public void insertTagCompany(Company company, Tag tag) {
        jdbcTemplate.update(INSERT_COMPANY_TAG_SQL, tag.getId(), company.getId());
    }

    /**
     * used PreparedStatementSetter and GeneratedKeyHolder
     */
    @Override
    public int insert(Company company) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(INSERT_SQL, new String[]{"id"});
                ps.setString(1, company.getName());
                ps.setString(2, company.getPhone());
                ps.setString(3, company.getEmail());
                ps.setString(4, company.getAddress());
                ps.setInt(5, company.getResponsibleUser() == null ? null : company.getResponsibleUser().getId());
                ps.setString(6, company.getWeb());
                ps.setBoolean(7, company.isDelete());
                ps.setInt(8, company.getCreator() == null ? null : company.getCreator().getId());
                ps.setTimestamp(9, new java.sql.Timestamp(company.getDateCreate() == null ? System.currentTimeMillis() : company.getDateCreate().getTime()));
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * used NamedParameterJdbcTemplate
     */
    @Override
    public void update(Company company) {
        SqlParameterSource namedParameters = new MapSqlParameterSource("name", company.getName())
                .addValue("phone", company.getPhone())
                .addValue("email", company.getEmail())
                .addValue("address", company.getAddress())
                .addValue("responsible_user_id", company.getResponsibleUser().getId())
                .addValue("web", company.getWeb())
                .addValue("deleted", company.isDelete())
                .addValue("created_by_id", company.getCreator().getId())
                .addValue("date_create", new Timestamp(company.getDateCreate().getTime()))
                .addValue("id", company.getId());
        namedParameterJdbcTemplate.update(UPDATE_SQL, namedParameters);
    }

    /**
     * used RowMapper
     */
    @Override
    public List<Company> getAll() {
        return jdbcTemplate.query(SELECT_ALL_SQL, new CompanyMapper());
    }

    /**
     * used RowMapper
     */
    @Override
    public Company getById(int id) {
        return jdbcTemplate.queryForObject(SELECT_BY_ID_SQL, new Object[]{id}, new CompanyMapper());
    }

    /**
     * used JdbcTemplate
     */
    @Override
    public void delete(int id) {
        jdbcTemplate.update(DELETE_SQL, id);
    }
}
