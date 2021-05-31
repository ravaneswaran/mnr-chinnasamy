package com.store.core.services;

import com.store.core.enums.UserStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.userService);
    }

    @Test
    public void testSignUp(){

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));
        Date newDate = new Date();

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = String.format("password-%s", randomNumberString);
        String status = UserStatus.SIGN_UP_VERIFICATION_PENDING.toString();

        int statusCode = this.userService.signUp(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, password, status, newDate, newDate);

        Assert.assertTrue(0 == statusCode);
    }

}
