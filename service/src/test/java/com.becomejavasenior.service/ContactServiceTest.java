package com.becomejavasenior.service;

import com.becomejavasenior.entity.*;
import com.becomejavasenior.jdbc.entity.*;
import com.becomejavasenior.jdbc.exceptions.DatabaseException;
import com.becomejavasenior.service.impl.ContactServiceImpl;
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

import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

public class ContactServiceTest {

    private static final int INT_0 = 0;
    private static final int INT_1 = 1;
    private static final String VAL_TIME_ALL_DAY = "task.time.all_day";
    private static final String VAL_FILENAME = "test_file.txt";

    private static final String[] VAL_1 = {"1"};
    private static final String[] VAL_EMPTY = {""};
    private static final String[] VAL_NAME = {"Default Name"};
    private static final String[] VAL_RESP_USER_ID = VAL_1;
    private static final String[] VAL_POSITION = {"sales manager"};
    private static final String[] VAL_TYPE_OF_PHONE = {"2"};
    private static final String[] VAL_PHONE = {"phone number 1"};
    private static final String[] VAL_EMAIL = {"default@email"};
    private static final String[] VAL_TASK_DATE = {"2020-10-15"};
    private static final String[] VAL_TASK_TIME = {"22"};
    private static final String[] VAL_TASK_TYPE = {"task type"};
    private static final String[] VAL_DEAL_BUDGET = {"155.55"};


    // parameter map field names
    private static final String FLD_CONTACT_NAME = "contact_name";
    private static final String FLD_CONTACT_USER_ID = "contact_user_id";
    private static final String FLD_CONTACT_POSITION = "contact_position";
    private static final String FLD_CONTACT_TYPE_OF_PHONE = "contact_type_of_phone";
    private static final String FLD_CONTACT_PHONE = "contact_phone";
    private static final String FLD_CONTACT_EMAIL = "contact_email";
    private static final String FLD_CONTACT_SKYPE = "contact_skype";
    private static final String FLD_CONTACT_TAG = "contact_tag";
    private static final String FLD_COMPANY_NEW = "company_new";
    private static final String FLD_COMPANY_NAME = "company_name";
    private static final String FLD_COMPANY_PHONE = "company_phone";
    private static final String FLD_COMPANY_EMAIL = "company_email";
    private static final String FLD_COMPANY_ADDRESS = "company_address";
    private static final String FLD_COMPANY_WEB = "company_web";
    private static final String FLD_DEAL_NAME = "deal_name";
    private static final String FLD_DEAL_STAGE_NAME = "deal_stage_name";
    private static final String FLD_DEAL_BUDGET = "deal_budget";
    private static final String FLD_NOTE_TEXT = "note_text";
    private static final String FLD_TASK_DATE = "task_date";
    private static final String FLD_TASK_TIME = "task_time";
    private static final String FLD_TASK_RESP_USER_ID = "task_responsible_user_id";
    private static final String FLD_TASK_TYPE = "task_type";
    private static final String FLD_TASK_TEXT = "task_text";

    @Mock
    private ContactDAO contactDAO;
    @Mock
    private CompanyDAO companyDAO;
    @Mock
    private TaskDAO taskDAO;
    @Mock
    private NoteDAO noteDAO;
    @Mock
    private StageDAO stageDAO;
    @Mock
    private DealDAO dealDAO;
    @Mock
    private FileDAO fileDAO;
    @Mock
    private UserDAO userDAO;
    @Mock
    private TagDAO tagDAO;

    @InjectMocks
    private ContactService contactService = new ContactServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        User currentUser = new User();
        currentUser.setId(INT_1);
        contactService.setCurrentUser(currentUser);
    }

    @Test
    public void testCreateByParameters() {
        Map<String, String[]> parameters = new HashMap<>();

        parameters.put(FLD_CONTACT_NAME, VAL_EMPTY);
        parameters.put(FLD_CONTACT_USER_ID, VAL_EMPTY);
        Assert.assertFalse(contactService.createByParameters(parameters, null));

        parameters.put(FLD_CONTACT_NAME, VAL_NAME);
        parameters.put(FLD_CONTACT_USER_ID, VAL_RESP_USER_ID);
        parameters.put(FLD_CONTACT_POSITION, VAL_POSITION);
        parameters.put(FLD_CONTACT_TYPE_OF_PHONE, VAL_TYPE_OF_PHONE);
        parameters.put(FLD_CONTACT_PHONE, VAL_PHONE);
        parameters.put(FLD_CONTACT_EMAIL, VAL_EMPTY);
        parameters.put(FLD_CONTACT_SKYPE, VAL_EMPTY);
        parameters.put(FLD_CONTACT_TAG, VAL_EMPTY);

        Contact noIdContact = new Contact();
        noIdContact.setName(VAL_NAME[INT_0]);
        when(contactDAO.insert(noIdContact)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Contact obj = (Contact) invocation.getArguments()[INT_0];
                obj.setId(INT_1);
                return obj.getId();
            }
        });

        parameters.put(FLD_COMPANY_NEW, VAL_1);
        parameters.put(FLD_COMPANY_NAME, VAL_NAME);
        parameters.put(FLD_COMPANY_PHONE, VAL_PHONE);
        parameters.put(FLD_COMPANY_EMAIL, VAL_EMAIL);
        parameters.put(FLD_COMPANY_ADDRESS, VAL_EMAIL);
        parameters.put(FLD_COMPANY_WEB, VAL_EMAIL);

        Company company = new Company();
        company.setName(VAL_NAME[INT_0]);
        when(companyDAO.getById(INT_1)).thenAnswer(new Answer<Company>() {
            @Override
            public Company answer(InvocationOnMock invocation) throws Throwable {
                company.setId((int) invocation.getArguments()[INT_0]);
                return company;
            }
        });
        when(companyDAO.insert(company)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Company obj = (Company) invocation.getArguments()[INT_0];
                obj.setId(INT_1);
                return obj.getId();
            }
        });

        parameters.put(FLD_DEAL_NAME, VAL_NAME);
        parameters.put(FLD_DEAL_STAGE_NAME, VAL_NAME);
        parameters.put(FLD_DEAL_BUDGET, VAL_DEAL_BUDGET);

        Stage stage = new Stage();
        stage.setName(VAL_NAME[INT_0]);
        when(stageDAO.insert(stage)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Stage obj = (Stage) invocation.getArguments()[INT_0];
                obj.setId(INT_1);
                return obj.getId();
            }
        });

        Deal deal = new Deal();
        deal.setName(VAL_NAME[INT_0]);
        when(dealDAO.insert(deal)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Deal obj = (Deal) invocation.getArguments()[INT_0];
                obj.setId(INT_1);
                return obj.getId();
            }
        });

        parameters.put(FLD_TASK_TEXT, VAL_NAME);
        parameters.put(FLD_TASK_DATE, VAL_TASK_DATE);
        parameters.put(FLD_TASK_TIME, VAL_TASK_TIME);
        parameters.put(FLD_TASK_RESP_USER_ID, VAL_1);
        parameters.put(FLD_TASK_TYPE, VAL_TASK_TYPE);
        Task task = new Task();
        task.setName(VAL_NAME[INT_0]);
        when(taskDAO.insert(task)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Task obj = (Task) invocation.getArguments()[INT_0];
                obj.setId(INT_1);
                return obj.getId();
            }
        });

        parameters.put(FLD_NOTE_TEXT, VAL_NAME);
        Note note = new Note();
        note.setNote(VAL_NAME[INT_0]);
        when(noteDAO.insert(note)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Note obj = (Note) invocation.getArguments()[INT_0];
                obj.setId(INT_1);
                return obj.getId();
            }
        });

        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId(INT_1);
        user.setName(VAL_NAME[INT_0]);
        userList.add(user);
        when(userDAO.getAll()).thenReturn(userList);

        Part testPart = new TestPart(VAL_FILENAME);

        assertTrue(contactService.createByParameters(parameters, testPart));
        verify(contactDAO).insert(any(Contact.class));
        verify(contactDAO).update(any(Contact.class));
        verify(companyDAO).insert(any(Company.class));
        verify(stageDAO).insert(any(Stage.class));
        verify(dealDAO).insert(any(Deal.class));
        verify(taskDAO).insert(any(Task.class));
        verify(noteDAO).insert(any(Note.class));
        verify(fileDAO).insert(any(File.class));
    }

    @Test(expected = DatabaseException.class)
    public void testInsertException() {
        Contact testContact = new Contact();
        testContact.setTypeOfPhone(TypeOfPhone.HOME);
        User responsibleCreatorUser = new User();
        responsibleCreatorUser.setId(INT_1);
        responsibleCreatorUser.setName(VAL_NAME[INT_0]);
        testContact.setResponsibleUser(responsibleCreatorUser);
        testContact.setCreator(responsibleCreatorUser);

        when(contactDAO.insert(testContact)).thenAnswer(new Answer<Integer>() {
            @Override
            public Integer answer(InvocationOnMock invocation) throws Throwable {
                Contact obj = (Contact) invocation.getArguments()[INT_0];
                boolean error = obj.getId() != INT_0 || obj.getTypeOfPhone() == null;
                error = error || obj.getResponsibleUser() == null || obj.getResponsibleUser().getId() == INT_0;
                error = error || obj.getCreator() == null || obj.getCreator().getId() == INT_0;
                if (error) {
                    throw new DatabaseException("mock exception");
                }
                obj.setId(INT_1);
                return obj.getId();
            }
        });

        assertEquals(INT_1, contactService.insert(testContact));
        verify(contactDAO).insert(any(Contact.class));
        assertEquals(INT_0, contactService.insert(testContact));
    }

    @Test
    public void testUpdateException() {
        contactService.update(any(Contact.class));
        verify(contactDAO).update(any(Contact.class));
    }

    @Test
    public void testGetAll() {
        List<Contact> contactList = new ArrayList<>();
        Contact testContact = new Contact();
        testContact.setId(INT_1);
        testContact.setName(VAL_NAME[INT_0]);
        contactList.add(testContact);
        when(contactDAO.getAll()).thenReturn(contactList);

        assertNotNull(contactService.getAll());
        assertEquals(INT_1, contactService.getAll().size());
        assertTrue(contactService.getAll() instanceof List);
        assertEquals(testContact, contactService.getAll().get(INT_0));
        verify(contactDAO, times(4)).getAll();
    }

    @Test
    public void testGetById() {
        Contact testContact = new Contact();
        testContact.setId(INT_1);
        testContact.setName(VAL_NAME[INT_0]);
        when(contactDAO.getById(INT_1)).thenReturn(testContact);

        assertNotNull(contactService.getById(INT_1));
        assertEquals(testContact, contactService.getById(INT_1));
        verify(contactDAO, times(2)).getById(anyInt());
    }

    @Test
    public void testDeleteException() {
        contactService.delete(INT_1);
        verify(contactDAO).delete(INT_1);
    }

    @Test
    public void testGetUserList() {
        List<User> userList = new ArrayList<>();
        User testUser = new User();
        testUser.setId(INT_1);
        testUser.setName(VAL_NAME[INT_0]);
        userList.add(testUser);
        when(userDAO.getAll()).thenReturn(userList);

        assertNotNull(contactService.getUserList());
        assertEquals(INT_1, contactService.getUserList().size());
        assertEquals(testUser, contactService.getUserList().get(INT_0));
        verify(userDAO, times(3)).getAll();
    }

    @Test
    public void testGetCompanyList() {
        List<Company> companyList = new ArrayList<>();
        Company testCompany = new Company();
        testCompany.setId(INT_1);
        testCompany.setName(VAL_NAME[INT_0]);
        companyList.add(testCompany);
        when(companyDAO.getAll()).thenReturn(companyList);

        assertNotNull(contactService.getCompanyList());
        assertEquals(INT_1, contactService.getCompanyList().size());
        assertEquals(testCompany, contactService.getCompanyList().get(INT_0));
        verify(companyDAO, times(3)).getAll();
    }

    @Test
    public void testGetStageList() {
        List<Stage> stageList = new ArrayList<>();
        Stage testStage = new Stage();
        testStage.setId(INT_1);
        testStage.setName(VAL_NAME[INT_0]);
        stageList.add(testStage);
        when(stageDAO.getAll()).thenReturn(stageList);

        assertNotNull(contactService.getStageList());
        assertEquals(INT_1, contactService.getStageList().size());
        assertEquals(testStage, contactService.getStageList().get(INT_0));
        verify(stageDAO, times(3)).getAll();
    }

    @Test
    public void testGetTaskTypesList() {
        List<String> taskTypeList = new ArrayList<>();
        taskTypeList.add(VAL_NAME[INT_0]);
        when(taskDAO.getAllTaskType()).thenReturn(taskTypeList);

        assertNotNull(contactService.getTaskTypesList());
        assertEquals(INT_1, contactService.getTaskTypesList().size());
        assertEquals(VAL_NAME[INT_0], contactService.getTaskTypesList().get(INT_0));
        verify(taskDAO, times(3)).getAllTaskType();
    }

    @Test
    public void testGetPhoneTypes() {
        assertArrayEquals(TypeOfPhone.values(), contactService.getPhoneTypes());
    }

    @Test
    public void testGetPeriodTypes() {
        assertArrayEquals(TypeOfPeriod.values(), contactService.getPeriodTypes());
    }

    @Test
    public void testGetTagList() {
        List<Tag> tagList = new ArrayList<>();
        Tag testTag = new Tag();
        testTag.setId(INT_1);
        testTag.setName(VAL_NAME[INT_0]);
        tagList.add(testTag);
        when(tagDAO.getAll()).thenReturn(tagList);

        assertNotNull(contactService.getTagList());
        assertEquals(INT_1, contactService.getTagList().size());
        assertEquals(testTag, contactService.getTagList().get(INT_0));
        verify(tagDAO, times(3)).getAll();
    }

    @Test
    public void testGetTaskTimeList() {
        assertNotNull(contactService.getTaskTimeList());
        assertEquals(49, contactService.getTaskTimeList().size());
        assertEquals(VAL_TIME_ALL_DAY, contactService.getTaskTimeList().get(INT_0));
    }

    @After
    public void tearDown() {
        contactDAO = null;
        companyDAO = null;
        taskDAO = null;
        noteDAO = null;
        stageDAO = null;
        dealDAO = null;
        fileDAO = null;
        userDAO = null;
        tagDAO = null;
        contactService = null;
    }

    private class TestPart implements Part {

        private String data;

        TestPart(String data) {
            this.data = data;
        }

        @Override
        public InputStream getInputStream() throws IOException {
            return new ByteArrayInputStream(data.getBytes());
        }

        @Override
        public String getContentType() {
            return null;
        }

        @Override
        public String getName() {
            return data;
        }

        @Override
        public String getSubmittedFileName() {
            return null;
        }

        @Override
        public long getSize() {
            return data.length();
        }

        @Override
        public void write(String fileName) throws IOException {

        }

        @Override
        public void delete() throws IOException {

        }

        @Override
        public String getHeader(String name) {
            return null;
        }

        @Override
        public Collection<String> getHeaders(String name) {
            return new ArrayList<>();
        }

        @Override
        public Collection<String> getHeaderNames() {
            return new ArrayList<>();
        }
    }
}
