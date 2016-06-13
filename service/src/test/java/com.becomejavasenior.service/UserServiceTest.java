package com.becomejavasenior.service;

import com.becomejavasenior.entity.Language;
import com.becomejavasenior.entity.User;
import com.becomejavasenior.jdbc.entity.LanguageDAO;
import com.becomejavasenior.jdbc.entity.UserDAO;
import com.becomejavasenior.jdbc.exceptions.DatabaseException;
import com.becomejavasenior.service.impl.UserServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    private static final int INT_1 = 1;
    private static final String SUPPORTED_LANG_ID = "1";
    private static final String NOT_SUPPORTED_LANG_ID = "0";
    private static final String DEF_LANG_CODE = "en";

    private static final String DEF_NAME = "Default Name";
    private static final String EMPTY_NAME = "";
    private static final String DEF_EMAIL = "default@email";
    private static final String NEW_EMAIL = "not_registered@email";
    private static final String DEF_PASSWORD = "123";

    private static final String ERR_USER_EXIST = "registration.error.userExist";
    private static final String ERR_LANG_NOT_SUPPORTED = "registration.error.noLanguage";
    private static final String ERR_SQL = "registration.error.unknown";

    @Mock
    private UserDAO userDAO;

    @Mock
    private LanguageDAO languageDAO;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLanguageList() {
        List<Language> languageList = new ArrayList<>();
        Language testLanguage = new Language();
        testLanguage.setId(INT_1);
        testLanguage.setName(DEF_NAME);
        testLanguage.setLanguageCode(DEF_LANG_CODE);
        languageList.add(testLanguage);

        when(languageDAO.getAll()).thenReturn(languageList);

        assertNotNull(userService.getLanguageList());
        assertEquals(INT_1, userService.getLanguageList().size());
        verify(languageDAO, times(2)).getAll();
    }

    @Test
    public void testGetUserMap() {
        List<User> userList = new ArrayList<>();
        User testUser = new User();
        testUser.setId(INT_1);
        testUser.setName(DEF_NAME);
        testUser.setEmail(DEF_EMAIL);
        userList.add(testUser);

        when(userDAO.getAll()).thenReturn(userList);

        assertNotNull(userService.getUserMap());
        assertEquals(INT_1, userService.getUserMap().size());
        verify(userDAO, times(2)).getAll();
    }

    @Test
    public void testCreateNewUser() {
        Language language = new Language();

        when(languageDAO.getById(INT_1)).thenReturn(language);

        List<User> userList = new ArrayList<>();
        User existentUser = new User();
        existentUser.setEmail(DEF_EMAIL);
        userList.add(existentUser);

        User noNameUser = new User();
        noNameUser.setName(EMPTY_NAME);

        User nonexistentGoodNameUser = new User();
        nonexistentGoodNameUser.setName(DEF_NAME);
        nonexistentGoodNameUser.setEmail(NEW_EMAIL);

        when(userDAO.getAll()).thenReturn(userList);
        when(userDAO.insert(noNameUser)).thenThrow(new DatabaseException(ERR_SQL));
        when(userDAO.insert(nonexistentGoodNameUser)).thenReturn(INT_1);

        // check if user (email) already exist
        assertTrue(userService.createNewUser(DEF_NAME, DEF_PASSWORD, DEF_EMAIL, SUPPORTED_LANG_ID)
                .containsKey(ERR_USER_EXIST));
        assertTrue(userService.createNewUser(DEF_NAME, DEF_PASSWORD, DEF_EMAIL, SUPPORTED_LANG_ID)
                .get(ERR_USER_EXIST).equals(DEF_EMAIL));

        // check if language not supported
        assertTrue(userService.createNewUser(DEF_NAME, DEF_PASSWORD, DEF_EMAIL, NOT_SUPPORTED_LANG_ID)
                .containsKey(ERR_LANG_NOT_SUPPORTED));
        assertTrue(userService.createNewUser(DEF_NAME, DEF_PASSWORD, DEF_EMAIL, NOT_SUPPORTED_LANG_ID)
                .get(ERR_LANG_NOT_SUPPORTED).equals(NOT_SUPPORTED_LANG_ID));

        // check if user name is empty - may take place only when gets new email and supported language
        assertTrue(userService.createNewUser(EMPTY_NAME, DEF_PASSWORD, NEW_EMAIL, SUPPORTED_LANG_ID)
                .containsKey(ERR_SQL));
        assertTrue(userService.createNewUser(EMPTY_NAME, DEF_PASSWORD, NEW_EMAIL, SUPPORTED_LANG_ID)
                .get(ERR_SQL).equals(NEW_EMAIL));

        // check no errors
        assertTrue(userService.createNewUser(DEF_NAME, DEF_PASSWORD, NEW_EMAIL, SUPPORTED_LANG_ID).isEmpty());

        // 2 times for error #3 and last one for no error
        verify(userDAO, times(3)).insert(any(User.class));
        verify(userDAO, times(7)).getAll();
    }

    @Test
    public void testGetUserLanguageCode() {
        User testUser = new User();
        when(userDAO.getUserLanguageCode(testUser)).thenReturn(DEF_LANG_CODE);

        assertEquals(DEF_LANG_CODE, userService.getUserLanguageCode(testUser));
        verify(userDAO).getUserLanguageCode(any(User.class));
    }

    @After
    public void tearDown() {
        userDAO = null;
        languageDAO = null;
        userService = null;
    }
}
