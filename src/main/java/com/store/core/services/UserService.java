package com.store.core.services;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface UserService {

    public int signUp(
            String firstName,
            String middleInitial,
            String lastName,
            String emailId,
            String uniqueId,
            String mobileNo,
            String password,
            String status,
            Date createdDate,
            Date modifiedDate);

}
