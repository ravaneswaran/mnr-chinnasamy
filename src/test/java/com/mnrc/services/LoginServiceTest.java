package com.mnrc.services;


import com.mnrc.enums.UserStatus;
import com.mnrc.enums.UserType;
import com.mnrc.models.User;
import com.mnrc.repositories.UserRepository;
import com.mnrc.ui.forms.Login;
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
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.loginService);
    }

    @Test
    public void testLogin(){
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
        user.setPassword(String.format("password%s", randomNumberString));
        user.setType(UserType.ADMIN.toString());
        user.setStatus(UserStatus.VERIFIED.toString());
        user.setCreatedDate(newDate);
        user.setModifiedDate(newDate);
        this.userRepository.save(user);

        Login login = this.loginService.login(emailId, password);

        Assert.assertEquals(user.getUUID(), login.getUserId());

    }
}
