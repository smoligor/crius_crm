package com.becomejavasenior.service;

import com.becomejavasenior.entity.Language;
import com.becomejavasenior.entity.User;
import com.becomejavasenior.jdbc.entity.LanguageDAO;
import com.becomejavasenior.service.impl.MailServiceImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import javax.mail.Message;
import javax.mail.Transport;

import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Transport.class)
public class MailServiceTest {

    private static final int DEF_ID = 1;
    private static final String DEF_LANG_CODE = "en";
    private static final String DEF_NAME = "Default Name";
    private static final String DEF_PASSWORD = "";
    private static final String DEF_EMAIL = "default@email";

    @Mock
    private LanguageDAO languageDAO;

    @InjectMocks
    private MailService mailService = new MailServiceImpl();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSendMail() throws Exception {
        Language defaultLanguage = new Language();
        defaultLanguage.setId(DEF_ID);
        defaultLanguage.setLanguageCode(DEF_LANG_CODE);

        when(languageDAO.getById(DEF_ID)).thenReturn(defaultLanguage);

        User recipientUser = new User();
        recipientUser.setName(DEF_NAME);
        recipientUser.setEmail(DEF_EMAIL);
        recipientUser.setPassword(DEF_PASSWORD);
        recipientUser.setLanguage(defaultLanguage);

        PowerMockito.mockStatic(Transport.class);
        PowerMockito.doNothing().when(Transport.class, "send", any(Message.class));

        mailService.sendMail(recipientUser);

        PowerMockito.verifyStatic();
        Transport.send(any(Message.class));

        verify(languageDAO).getById(anyInt());
    }

    @After
    public void tearDown() {
        languageDAO = null;
        mailService = null;
    }
}
