package com.mnrc.administration.services;

import com.mnrc.administration.enums.UserStatus;
import com.mnrc.administration.enums.UserType;
import com.mnrc.administration.models.User;
import com.mnrc.administration.repositories.UserRepository;
import com.mnrc.administration.ui.forms.ChangePasswordForm;
import com.mnrc.administration.ui.forms.ForgotPasswordForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PasswordServiceTest {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.passwordService);
    }

    @Test
    public void testForgotPassword(){
        String uuid = UUID.randomUUID().toString();

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        Date newDate = new Date();

        String emailId = String.format("mail%s@test.com", randomNumberString);
        String password = String.format("password%s", randomNumberString);

        User user = new User();
        user.setUUID(uuid);
        user.setFirstName("Ravaneswaran");
        user.setMiddleInitial("");
        user.setLastName("Chinnasamy");
        user.setEmailId(emailId);
        user.setUniqueId(randomNumberString);
        user.setMobileNo(randomNumberString);
        user.setPassword(password);
        user.setType(UserType.ADMIN.toString());
        user.setStatus(UserStatus.VERIFIED.toString());
        user.setCreatedDate(newDate);
        user.setModifiedDate(newDate);
        this.userRepository.save(user);

        ForgotPasswordForm forgotPassword = this.passwordService.forgotPassword(emailId);

        Assert.assertEquals(uuid, forgotPassword.getUserId());
        Assert.assertEquals(emailId, forgotPassword.getEmailId());
        Assert.assertEquals(password, forgotPassword.getPassword());
    }

    @Test
    public void testChangePassword(){
        String uuid = UUID.randomUUID().toString();

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        Date newDate = new Date();

        String emailId = String.format("mail%s@test.com", randomNumberString);
        String password = String.format("password%s", randomNumberString);

        User user = new User();
        user.setUUID(uuid);
        user.setFirstName("Ravaneswaran");
        user.setMiddleInitial("");
        user.setLastName("Chinnasamy");
        user.setEmailId(emailId);
        user.setUniqueId(randomNumberString);
        user.setMobileNo(randomNumberString);
        user.setPassword(password);
        user.setType(UserType.ADMIN.toString());
        user.setStatus(UserStatus.VERIFIED.toString());
        user.setCreatedDate(newDate);
        user.setModifiedDate(newDate);
        this.userRepository.save(user);

        ChangePasswordForm changePassword = this.passwordService.changePassword(emailId, password, "test");

        Assert.assertEquals(uuid, changePassword.getUserId());
        Assert.assertEquals(emailId, changePassword.getEmailId());
        Assert.assertEquals("test", changePassword.getOldPassword());
    }
}
