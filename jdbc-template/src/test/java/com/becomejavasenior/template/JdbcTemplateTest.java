package com.becomejavasenior.template;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-template.xml")
public class JdbcTemplateTest {

    private static final String SQL_JDBC_TEMPLATE_TEST = "SELECT count(*) AS col1 " +
            "FROM information_schema.tables WHERE table_name = 'language'";

    @Autowired
    private DataSource dataSource;

    private JdbcTemplate jdbcTemplate;

    @Before
    public void setUp() {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Test
    public void openConnectionTest() throws SQLException {

        assertNotNull("DataSource does not exist", dataSource);
        Connection connection = jdbcTemplate.getDataSource().getConnection();
        assertNotNull("connection does not exist", connection);
        assertFalse("connection must not be closed", connection.isClosed());
        assertFalse("connection must not be read only", connection.isReadOnly());
        assertEquals("query can't execute or system table does not exist", 1L,
                jdbcTemplate.queryForMap(SQL_JDBC_TEMPLATE_TEST).get("col1"));
        connection.close();
        Assert.assertTrue("finally connection must be closed", connection.isClosed());
    }
}
