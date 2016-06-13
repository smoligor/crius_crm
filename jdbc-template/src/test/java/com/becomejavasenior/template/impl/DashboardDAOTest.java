package com.becomejavasenior.template.impl;

import com.becomejavasenior.template.DashboardDAO;
import com.becomejavasenior.template.DashboardLogBox;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-template.xml")
public class DashboardDAOTest {

    @Autowired
    private DashboardDAO dashboardDAO;

    @Test
    public void testSpringDI() {
        assertNotNull(dashboardDAO);
    }

    @Test
    public void testGetDealStat() {
        assertNotNull(dashboardDAO.getDealStat());
        Map<String, Object> statMap = dashboardDAO.getDealStat();
        assertEquals(5, statMap.size());
        assertTrue(statMap.containsKey("deal_count"));
        assertTrue(statMap.containsKey("deal_sum"));
        assertTrue(statMap.containsKey("deal_finished_ok"));
        assertTrue(statMap.containsKey("deal_finished_fail"));
        assertTrue(statMap.containsKey("deal_with_task"));
    }

    @Test
    public void testGetDealStatRepeating150() {
        int counter = 150;
        while (counter-- > 0) {
            testGetDealStat();
        }
    }

    @Test
    public void testGetTaskStat() {
        assertNotNull(dashboardDAO.getTaskStat());
        Map<String, Object> statMap = dashboardDAO.getTaskStat();
        assertEquals(3, statMap.size());
        assertTrue(statMap.containsKey("task_in_work"));
        assertTrue(statMap.containsKey("task_done"));
        assertTrue(statMap.containsKey("task_paused"));
    }

    @Test
    public void testGetCompanyStat() {
        assertNotNull(dashboardDAO.getCompanyStat());
        Map<String, Object> statMap = dashboardDAO.getCompanyStat();
        assertEquals(1, statMap.size());
        assertTrue(statMap.containsKey("company_count"));
    }

    @Test
    public void testGetContactStat() {
        assertNotNull(dashboardDAO.getContactStat());
        Map<String, Object> statMap = dashboardDAO.getContactStat();
        assertEquals(1, statMap.size());
        assertTrue(statMap.containsKey("contact_count"));
    }

    @Test
    public void testGetLog20() {
        int rowCount = 20;
        assertNotNull(dashboardDAO.getLog(rowCount));
        List<DashboardLogBox> logBoxList = dashboardDAO.getLog(rowCount);
        assertTrue(logBoxList.size() <= rowCount);
        for (int i = 0; i < logBoxList.size() - 1; i++) {
            DashboardLogBox box1 = logBoxList.get(i);
            DashboardLogBox box2 = logBoxList.get(i + 1);

            assertTrue(String.format("log items not sorted: #%d #%d, (%3$TD %3$TT should be >= %4$TD %4$TT)",
                    i, i + 1, box1.getDateCreate(), box2.getDateCreate()),
                    box1.getDateCreate().after(box2.getDateCreate()));
        }

        for (int i = 0; i < logBoxList.size(); i++) {
            DashboardLogBox box1 = logBoxList.get(i);
            assertNotNull(box1.getDateCreate());
            assertNotNull(box1.getLogType());
            assertNotNull(box1.getLogInfo());
            assertNotNull(box1.getUserName());
        }
    }
}
