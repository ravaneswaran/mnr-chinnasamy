package com.mnrc.administration.repositories;

import com.mnrc.administration.models.User;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class AbstractRepositoryTest {

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
}
