package com.store.core.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailerUtilTest {

    @Autowired
    private MailerUtil mailerUtil;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.mailerUtil);
    }

    @Test
    public void testSendMailMessageWithoutAttachment(){
        String from = "ravaneswaran@gmail.com";
        String to = "testuser@gmail.com";
        String subject = "Test Subject";
        String text = "Test Message";

        try {
            this.mailerUtil.sendMailMessage(from, to, subject, text);
            Assert.assertTrue(0 == 0);
        } catch (MessagingException e) {
            e.printStackTrace();
            Assert.assertTrue(0 == 1);
        }
    }
}
