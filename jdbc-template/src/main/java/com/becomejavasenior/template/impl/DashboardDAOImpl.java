package com.becomejavasenior.template.impl;

import com.becomejavasenior.template.DashboardDAO;
import com.becomejavasenior.template.DashboardLogBox;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class DashboardDAOImpl implements DashboardDAO {

    private static final String LOG_PREFIX = "dashboard.log.add_";
    private static final String FLD_NAME = "name";
    private static final String TBL_DEAL = "deal";
    private static final String TBL_COMPANY = "company";
    private static final String TBL_CONTACT = "contact";
    private static final String TBL_TASK = "task";
    private static final String TBL_FILE = "attached_file";
    private static final String TBL_NOTE = "note";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(BasicDataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Map<String, Object> getDealStat() {
        String sql = "SELECT count(deal.id) AS deal_count, cast(sum(deal.amount) * 100 AS INT) AS deal_sum," +
                " count(task.deal_id) AS deal_with_task,\n" +
                " count(CASE WHEN stage_id = 5 THEN 1 ELSE NULL END) AS deal_finished_ok,\n" +
                " count(CASE WHEN stage_id = 6 THEN 1 ELSE NULL END) AS deal_finished_fail\n" +
                "FROM deal LEFT JOIN task ON deal.id = task.deal_id WHERE NOT deal.deleted AND NOT task.deleted";
        return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public Map<String, Object> getTaskStat() {
        String sql = "SELECT count(CASE WHEN task_status.id = 1 THEN 1 END) AS task_in_work,\n" +
                " count(CASE WHEN task_status.id = 2 THEN 1 END) AS task_done,\n" +
                " count(CASE WHEN task_status.id = 3 THEN 1 END) AS task_paused\n" +
                "FROM task LEFT JOIN task_status ON task.status_id = task_status.id WHERE NOT task.deleted";
        return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public Map<String, Object> getCompanyStat() {
        String sql = "SELECT count(*) AS company_count FROM company WHERE NOT deleted";
        return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public Map<String, Object> getContactStat() {
        String sql = "SELECT count(*) AS contact_count FROM contact WHERE NOT deleted";
        return jdbcTemplate.queryForMap(sql);
    }

    @Override
    public List<DashboardLogBox> getLog(int maxRowCount) {
        List<DashboardLogBox> logBoxList = new ArrayList<>();
        TreeSet<DashboardLogBox> logBoxTreeSet = new TreeSet<>();

        logBoxTreeSet.addAll(getTableLog(LOG_PREFIX + TBL_COMPANY, FLD_NAME, TBL_COMPANY, maxRowCount));
        logBoxTreeSet.addAll(getTableLog(LOG_PREFIX + TBL_CONTACT, FLD_NAME, TBL_CONTACT, maxRowCount));
        logBoxTreeSet.addAll(getTableLog(LOG_PREFIX + TBL_DEAL, FLD_NAME, TBL_DEAL, maxRowCount));
        logBoxTreeSet.addAll(getTableLog(LOG_PREFIX + TBL_TASK, FLD_NAME, TBL_TASK, maxRowCount));
        logBoxTreeSet.addAll(getFileNoteLog(LOG_PREFIX + TBL_FILE, TBL_FILE, maxRowCount));
        logBoxTreeSet.addAll(getFileNoteLog(LOG_PREFIX + TBL_NOTE, TBL_NOTE, maxRowCount));

        Iterator<DashboardLogBox> boxIterator = logBoxTreeSet.descendingIterator();
        for (int i = 0; i < maxRowCount && boxIterator.hasNext(); i++) {
            logBoxList.add(boxIterator.next());
        }
        return logBoxList;
    }

    private List<DashboardLogBox> getTableLog(String logType, String infoFieldName, String tableName, int maxRowCount) {

        String sql = "SELECT tbl.date_create, usr.name AS user_name, ?, tbl." + infoFieldName +
                "\nFROM " + tableName + " AS tbl LEFT JOIN \"user\" AS usr ON tbl.created_by_id = usr.id\n" +
                "ORDER BY tbl.date_create DESC LIMIT ?";

        return jdbcTemplate.query(sql, new DashboardLogBoxMapper(), logType, maxRowCount);
    }

    private List<DashboardLogBox> getFileNoteLog(String logType, String tableName, int maxRowCount) {

        String sql = "SELECT tbl.date_create, usr.name, ?," +
                "  CASE WHEN tbl.company_id IS NOT NULL THEN company.name ELSE\n" +
                "  CASE WHEN tbl.contact_id IS NOT NULL THEN contact.name ELSE\n" +
                "  CASE WHEN tbl.deal_id IS NOT NULL THEN deal.name ELSE '' END END END\n" +
                "FROM " + tableName + " AS tbl LEFT JOIN company ON tbl.company_id = company.id\n" +
                "  LEFT JOIN contact ON tbl.contact_id = contact.id\n" +
                "  LEFT JOIN deal ON tbl.deal_id = deal.id\n" +
                "  LEFT JOIN \"user\" AS usr ON tbl.created_by_id = usr.id\n" +
                "ORDER BY tbl.date_create DESC LIMIT ?\n";

        return jdbcTemplate.query(sql, new DashboardLogBoxMapper(), logType, maxRowCount);
    }

    private static class DashboardLogBoxMapper implements RowMapper<DashboardLogBox> {
        @Override
        public DashboardLogBox mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new DashboardLogBox(rs.getTimestamp(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
    }
}
