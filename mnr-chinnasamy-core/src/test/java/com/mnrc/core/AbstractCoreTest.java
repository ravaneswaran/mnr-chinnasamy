package com.mnrc.core;

import com.mnrc.core.entities.User;
import com.mnrc.core.entities.UserRole;
import net.bytebuddy.utility.RandomString;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class AbstractCoreTest {

    protected User getTestUser(){
        String uuid = UUID.randomUUID().toString();

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        Date newDate = new Date();

        User user = new User();
        user.setUUID(uuid);
        user.setFirstName("Test");
        user.setMiddleInitial("");
        user.setLastName("Test");
        user.setEmailId(String.format("mail%s@test.com", randomNumberString));
        user.setUniqueId(randomNumberString);
        user.setMobileNo(randomNumberString);
        user.setPassword(String.format("password%s", randomNumberString));
        user.setStatus("YET-TO-VERIFY");
        user.setCreatedDate(newDate);
        user.setModifiedDate(newDate);

        return user;
    }

    protected UserRole getTestRole(){
        UserRole userRole = new UserRole();
        Date newDate = new Date();

        userRole.setName(RandomString.make().toUpperCase());
        userRole.setCreatedBy("Almighty");
        userRole.setModifiedBy("Almighty");
        userRole.setCreatedDate(newDate);
        userRole.setModifiedDate(newDate);

        return userRole;
    }
}
