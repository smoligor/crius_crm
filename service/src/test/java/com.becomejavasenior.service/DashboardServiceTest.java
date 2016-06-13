package com.becomejavasenior.service;

import com.becomejavasenior.jdbc.entity.CurrencyDAO;
import com.becomejavasenior.service.DashboardService;
import com.becomejavasenior.service.impl.DashboardServiceImpl;
import com.becomejavasenior.template.DashboardDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

public class DashboardServiceTest {

    @Mock
    private DashboardDAO dashboardDAO;

    @Mock
    private CurrencyDAO currencyDAO;

    @InjectMocks
    private DashboardService dashboardService = new DashboardServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInitialization() {
        assertNotNull(dashboardDAO);
        assertNotNull(currencyDAO);
        assertNotNull(dashboardService);
    }

    @Test
    public void testGetDealStat() {
        assertNotNull(dashboardService.getDealStat());
        verify(dashboardDAO).getDealStat();
    }

    @Test
    public void testGetTaskStat() {
        assertNotNull(dashboardService.getTaskStat());
        verify(dashboardDAO).getTaskStat();
    }

    @Test
    public void testGetCompanyStat() {
        assertNotNull(dashboardService.getCompanyStat());
        verify(dashboardDAO).getCompanyStat();
    }

    @Test
    public void testGetContactStat() {
        assertNotNull(dashboardService.getContactStat());
        verify(dashboardDAO).getContactStat();
    }

    @Test
    public void testGetActiveCurrency() {
        assertNotNull(dashboardService.getActiveCurrency());
        verify(currencyDAO).getActiveCurrency();
    }

    @Test
    public void testGetLogBoxList() {
        assertNotNull(dashboardService.getLogBoxList(anyInt()));
        verify(dashboardDAO).getLog(anyInt());
    }

    @After
    public void tearDown() {
        dashboardDAO = null;
        currencyDAO = null;
        dashboardService = null;
    }
}
