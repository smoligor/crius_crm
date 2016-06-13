package com.becomejavasenior.service;

import com.becomejavasenior.entity.Company;
import com.becomejavasenior.entity.Contact;
import com.becomejavasenior.entity.Deal;
import com.becomejavasenior.entity.Stage;
import com.becomejavasenior.service.impl.DealServiceImpl;
import com.becomejavasenior.template.DealDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.*;

public class DealServiceTest {

    private static final int INT_1 = 1;
    private static final String DEF_NAME = "Default Name";

    @Mock
    private DealDAO dealDAO;

    @InjectMocks
    private DealService dealService = new DealServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsert() {
        Deal testDeal = new Deal();
        testDeal.setCompany(new Company());
        testDeal.setName(DEF_NAME);
        when(dealDAO.insert(testDeal)).thenReturn(INT_1);

        assertEquals(INT_1, dealService.insert(testDeal));
        verify(dealDAO).insert(any(Deal.class));
    }

    @Test
    public void testGetAllDealsByStage() {
        Stage testStage = new Stage();
        testStage.setId(INT_1);
        testStage.setName(DEF_NAME);

        List<Deal> dealList = new ArrayList<>();
        Deal testDeal = new Deal();
        testDeal.setId(INT_1);
        testDeal.setName(DEF_NAME);
        testDeal.setStage(testStage);
        dealList.add(testDeal);
        when(dealDAO.getDealsByStage(DEF_NAME)).thenReturn(dealList);

        assertNotNull(dealService.getAllDealsByStage(DEF_NAME));
        assertEquals(INT_1, dealService.getAllDealsByStage(DEF_NAME).size());
        verify(dealDAO, times(2)).getDealsByStage(anyString());
    }

    @Test
    public void testGetContactsByDealId() {
        List<Contact> contactList = new ArrayList<>();
        Contact testContact = new Contact();
        testContact.setId(INT_1);
        testContact.setName(DEF_NAME);
        contactList.add(testContact);
        when(dealDAO.getContactsByDealId(INT_1)).thenReturn(contactList);

        assertNotNull(dealService.getContactsByDealId(INT_1));
        assertEquals(INT_1, dealService.getContactsByDealId(INT_1).size());
        verify(dealDAO, times(2)).getContactsByDealId(anyInt());
    }

    @Test
    public void testGetAllStage() {
        List<Stage> stageList = new ArrayList<>();
        Stage testStage = new Stage();
        testStage.setId(INT_1);
        testStage.setName(DEF_NAME);
        stageList.add(testStage);
        when(dealDAO.getAllStage()).thenReturn(stageList);

        assertNotNull(dealService.getAllStage());
        assertEquals(INT_1, dealService.getAllStage().size());
        verify(dealDAO, times(2)).getAllStage();
    }

    @Test
    public void testGetDealsForList() {
        dealService.getDealsForList();
        verify(dealDAO).getDealsForList();
    }

    @Test
    public void testUpdate() {
        dealService.update(any(Deal.class));
        verify(dealDAO).update(any(Deal.class));
    }

    @Test
    public void testGetAll() {
        dealService.getAll();
        verify(dealDAO).getAll();
    }

    @Test
    public void testById() {
        dealService.getById(INT_1);
        verify(dealDAO).getById(INT_1);
    }

    @Test
    public void testDelete() {
        dealService.delete(INT_1);
        verify(dealDAO).delete(INT_1);
    }

    @After
    public void tearDown() {
        dealDAO = null;
        dealService = null;
    }
}
