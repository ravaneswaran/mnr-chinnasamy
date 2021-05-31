package com.store.core.services.impl;

import com.store.core.models.User;
import com.store.core.repositories.UserRepository;
import com.store.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public int signUp(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String password, String status, Date createdDate, Date modifiedDate) {
        User user = new User();

        user.setFirstName(firstName);
        user.setMiddleInitial(middleInitial);
        user.setLastName(lastName);
        user.setEmailId(emailId);
        user.setUniqueId(uniqueId);
        user.setMobileNo(mobileNo);
        user.setPassword(password);
        user.setStatus(status);
        user.setCreatedDate(createdDate);
        user.setModifiedDate(modifiedDate);

        System.out.println("=====================================>>>>>>>");

        this.userRepository.save(user);

        return 0;
    }
}
