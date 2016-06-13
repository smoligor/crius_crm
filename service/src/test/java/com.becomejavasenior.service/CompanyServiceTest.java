package com.becomejavasenior.service;

import com.becomejavasenior.entity.*;
import com.becomejavasenior.jdbc.entity.*;
import com.becomejavasenior.service.impl.CompanyServiceImpl;
import com.becomejavasenior.template.CompanyDAO;
import com.becomejavasenior.template.DealDAO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class CompanyServiceTest {

    private static final int INT_0 = 0;
    private static final int INT_1 = 1;
    private static final String VAL_NAME = "Default Name";

    @Mock
    private CompanyDAO companyDAO;

    @Mock
    private UserDAO userDAO;

    @Mock
    private ContactDAO contactDAO;

    @Mock
    private TaskDAO taskDAO;

    @Mock
    private DealDAO dealDAO;

    @Mock
    private NoteDAO noteDAO;

    @Mock
    private TagDAO tagDAO;

    @Mock
    private FileDAO fileDAO;

    @InjectMocks
    private CompanyService companyService = new CompanyServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testInsert() {
        companyService.insert(any(Company.class));
        verify(companyDAO).insert(any(Company.class));
    }

    @Test
    public void testUpdate() {
        companyService.update(any(Company.class));
        verify(companyDAO).update(any(Company.class));
    }

    @Test
    public void testGetAll() {
        companyService.getAll();
        verify(companyDAO).getAll();
    }

    @Test
    public void testGetById() {
        companyService.getById(INT_1);
        verify(companyDAO).getById(INT_1);
    }

    @Test
    public void testDelete() {
        companyService.delete(INT_1);
        verify(companyDAO).delete(INT_1);
    }

    @Test
    public void testGetResponsibleUserList() {
        companyService.getResponsibleUserList();
        verify(userDAO).getAll();
    }

    @Test
    public void testGetContactList() {
        companyService.getContactList();
        verify(contactDAO).getAll();
    }

    @Test
    public void testGetTaskTypeList() {
        companyService.getTaskTypeList();
        verify(taskDAO).getTaskTypeList();
    }

    @Test
    public void testGetStageDealsList() {
        companyService.getStageDealsList();
        verify(dealDAO).getStageDealsList();
    }

    @Test
    public void testGetTimeListForTask() {
        Assert.assertNotNull(companyService.getTimeListForTask());
        Assert.assertEquals(49, companyService.getTimeListForTask().size());
    }

    @Test
    public void testCreateNewCompany() {

        Company company = new Company();
        company.setName(VAL_NAME);

        when(companyDAO.insert(company)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Company obj = invocation.getArgumentAt(0, Company.class);
                obj.setId(1);
                return obj.getId();
            }
        });

        Contact contact = new Contact();
        contact.setName(VAL_NAME);
        List<Contact> contactList = new ArrayList<>();
        contactList.add(contact);
        company.setContacts(contactList);
        when(contactDAO.insert(contact)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Contact obj = invocation.getArgumentAt(0, Contact.class);
                obj.setId(INT_1);
                return obj.getId();
            }
        });

        Deal deal = new Deal();
        deal.setName(VAL_NAME);
        when(dealDAO.insert(deal)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Deal obj = invocation.getArgumentAt(0, Deal.class);
                obj.setId(1);
                return obj.getId();
            }
        });

        Task task = new Task();
        task.setName(VAL_NAME);
        when(taskDAO.insert(task)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Task obj = invocation.getArgumentAt(0, Task.class);
                obj.setId(1);
                return obj.getId();
            }
        });

        Note note = new Note();
        note.setNote(VAL_NAME);
        List<Note> noteList = new ArrayList<>();
        noteList.add(note);
        company.setNotes(noteList);
        when(noteDAO.insert(note)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Note obj = invocation.getArgumentAt(0, Note.class);
                obj.setId(1);
                return obj.getId();
            }
        });

        Tag tag = new Tag();
        tag.setName(VAL_NAME);
        List<Tag> tagList = new ArrayList<>();
        tagList.add(tag);
        company.setTags(tagList);
        when(tagDAO.insert(tag)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Tag obj = invocation.getArgumentAt(0, Tag.class);
                obj.setId(1);
                return obj.getId();
            }
        });

        doAnswer(new Answer<Void>() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                Company company1 = invocation.getArgumentAt(INT_0, Company.class);
                Tag tag1 = invocation.getArgumentAt(INT_1, Tag.class);
                if (company1.getId() < INT_1 || tag1.getId() < INT_1) {
                    throw new Exception("mock exception @ companyDAO.insertTagCompany(...)");
                }
                return null;
            }

        }).when(companyDAO).insertTagCompany(company, tag);

        File file = new File();
        file.setFileName(VAL_NAME);
        when(fileDAO.insert(file)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                File obj = invocation.getArgumentAt(0, File.class);
                obj.setId(1);
                return obj.getId();
            }
        });

        assertEquals(INT_1, companyService.createNewCompany(company, contact, deal, task, file));
        verify(companyDAO).insert(any(Company.class));
        verify(companyDAO).insertTagCompany(any(Company.class), any(Tag.class));
        verify(contactDAO).insert(any(Contact.class));
        verify(dealDAO).insert(any(Deal.class));
        verify(taskDAO).insert(any(Task.class));
        verify(noteDAO).insert(any(Note.class));
        verify(tagDAO).insert(any(Tag.class));
        verify(fileDAO).insert(any(File.class));
    }

    @After
    public void tearDown() {
        contactDAO = null;
        companyDAO = null;
        taskDAO = null;
        noteDAO = null;
        dealDAO = null;
        fileDAO = null;
        userDAO = null;
        tagDAO = null;
        companyService = null;
    }
}
