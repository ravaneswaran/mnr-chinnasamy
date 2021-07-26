package com.mnrc.administration.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.mailService);
    }

    @Test
    public void testSendUserVerificationMail(){
        String tokenId = UUID.randomUUID().toString();
        String firstName = "FirstName";
        String middleInitial = "M";
        String lastName = "LastName";
        String emailId = "test@test.com";

        int result = this.mailService.sendUserVerificationMail(tokenId, firstName, middleInitial, lastName, emailId);

        Assert.assertEquals(0, result);
    }

    @Test
    public void testSendForgotPasswordMail(){
        String firstName = "FirstName";
        String middleInitial = "M";
        String lastName = "LastName";
        String emailId = "test@test.com";

        int result = this.mailService.sendForgotPasswordMail(firstName, middleInitial, lastName, "test-password", emailId,"Your Password for your account");

        Assert.assertEquals(0, result);
    }
}
