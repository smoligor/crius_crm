package com.becomejavasenior.template.impl;

import com.becomejavasenior.entity.Company;
import com.becomejavasenior.entity.User;
import com.becomejavasenior.jdbc.ConnectionPool;
import com.becomejavasenior.template.CompanyDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-template.xml")
public class CompanyDAOTest {

    private static final String DEFAULT_NAME = "Default Name";
    private static final String DEFAULT_PHONE = "Default Phone Number";
    private static final String DEFAULT_EMAIL = "default@email.org";
    private static final String DEFAULT_ADDRESS = "Default Company Address";
    private static final Timestamp DEFAULT_DATE = new Timestamp(new Date().getTime());
    @Autowired
    private CompanyDAO companyDAO;
    private User userForCompanyTest;
    private User userForCompanyUpdateTest;
    private int companyTestId;

    public CompanyDAOTest() {
        userForCompanyTest = new User();
        userForCompanyTest.setId(1);
        userForCompanyUpdateTest = new User();
        userForCompanyUpdateTest.setId(2);
    }

    @Before
    public void setUp() {
        companyTestId = 0;
    }

    @After
    public void tearDown() throws SQLException {
        if (companyTestId > 0) {
            try (Connection connection = ConnectionPool.getConnection();
                 Statement statement = connection.createStatement()) {
                statement.executeUpdate("DELETE FROM company WHERE id = " + Integer.toString(companyTestId));
            } catch (SQLException e) {
                throw new SQLException("Test Company clean up failed!", e);
            }
            companyTestId = 0;
        }
    }

    @Test
    public void testCreate() {
        Company companyTest = new Company();
        Assert.assertTrue("Company ID before creation must be '0'", companyTest.getId() == 0);

        try {
            companyTestId = companyDAO.insert(companyTest);
        } catch (Exception e) {
            Assert.assertTrue("Empty company ID must be '0'", companyTest.getId() == 0);
        } finally {
            Assert.assertTrue("Empty company ID must be '0'", companyTestId == 0);
        }

        companyTest.setName(DEFAULT_NAME);
        companyTest.setPhone(DEFAULT_PHONE);
        companyTest.setEmail(DEFAULT_EMAIL);
        companyTest.setAddress(DEFAULT_ADDRESS);
        companyTest.setDateCreate(DEFAULT_DATE);
        companyTest.setCreator(userForCompanyTest);
        companyTest.setResponsibleUser(userForCompanyTest);
        companyTestId = companyDAO.insert(companyTest);

        Assert.assertNotNull("Company creation failed", companyTest);
        Assert.assertTrue("Company ID after creation must be not '0'", companyTestId > 0);
        Assert.assertNotNull("Company date of creation must be not null", companyTest.getDateCreate());
        Assert.assertNotNull("Company creator must be not null", companyTest.getCreator());
    }

    @Test
    public void testGetByPK() {
        Assert.assertNotNull("Company read by PK failed", companyDAO.getById(1));
    }

    @Test
    public void testUpdate() {
        String updatedName = "Updated Name";
        String updatedPhone = "Updated Phone";
        String updatedEmail = "updated@email.org";
        String updatedAddress = "Updated Company Address";
        String updatedWeb = "http://updated.www.address";
        Date updatedCreateDate = new Timestamp(1L << 41);

        Company companyTest = new Company();
        companyTest.setName(DEFAULT_NAME);
        companyTest.setPhone(DEFAULT_PHONE);
        companyTest.setEmail(DEFAULT_EMAIL);
        companyTest.setAddress(DEFAULT_ADDRESS);
        companyTest.setDateCreate(DEFAULT_DATE);
        companyTest.setCreator(userForCompanyTest);
        companyTest.setResponsibleUser(userForCompanyTest);
        companyTestId = companyDAO.insert(companyTest);

        Assert.assertNotNull("Company before update must not be null", companyTest);
        companyTest.setName(updatedName);
        companyTest.setPhone(updatedPhone);
        companyTest.setEmail(updatedEmail);
        companyTest.setAddress(updatedAddress);
        companyTest.setWeb(updatedWeb);
        companyTest.setDateCreate(updatedCreateDate);
        companyTest.setCreator(userForCompanyUpdateTest);
        companyTest.setResponsibleUser(userForCompanyUpdateTest);
        companyTest.setId(companyTestId);

        companyDAO.update(companyTest);

        Company updatedCompany = companyDAO.getById(companyTestId);
        Assert.assertNotNull("Company after update is null", updatedCompany);
        Assert.assertEquals("Company name update failed", updatedName, updatedCompany.getName());
        Assert.assertEquals("Company phone update failed", updatedPhone, updatedCompany.getPhone());
        Assert.assertEquals("Company email update failed", updatedEmail, updatedCompany.getEmail());
        Assert.assertEquals("Company address update failed", updatedAddress, updatedCompany.getAddress());
        Assert.assertEquals("Company web address update failed", updatedWeb, updatedCompany.getWeb());
        Assert.assertEquals("Date of company creation update failed", updatedCreateDate, updatedCompany.getDateCreate());
        Assert.assertEquals("Company creator update failed", userForCompanyUpdateTest.getId(), updatedCompany.getCreator().getId());
        Assert.assertEquals("Company responsible user update failed", userForCompanyUpdateTest.getId(), updatedCompany.getResponsibleUser().getId());
    }

    @Test
    public void testDelete() {
        Company companyTest = new Company();
        companyTest.setName(DEFAULT_NAME);
        companyTest.setPhone(DEFAULT_PHONE);
        companyTest.setEmail(DEFAULT_EMAIL);
        companyTest.setAddress(DEFAULT_ADDRESS);
        companyTest.setDateCreate(DEFAULT_DATE);
        companyTest.setCreator(userForCompanyTest);
        companyTest.setResponsibleUser(userForCompanyTest);
        companyTestId = companyDAO.insert(companyTest);

        List companyList = companyDAO.getAll();
        int oldListSize = companyList.size();
        Assert.assertTrue("Company list must not be empty", oldListSize > 0);
        companyDAO.delete(companyTestId);
        companyList = companyDAO.getAll();
        Assert.assertEquals("Company delete test failed", 1, oldListSize - companyList.size());
    }

    @Test
    public void testGetAll() {
        List<Company> companyList = companyDAO.getAll();
        Assert.assertNotNull("Company list must not be null", companyList);
        Assert.assertTrue("Company list must not be empty", companyList.size() > 0);
    }
}
