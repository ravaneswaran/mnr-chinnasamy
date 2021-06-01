package com.webshoppe.services.impl;

import com.webshoppe.models.User;
import com.webshoppe.repositories.UserRepository;
import com.webshoppe.services.UserService;
import com.webshoppe.valueobj.SignUpVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public SignUpVO signUp(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String password, String status, Date createdDate, Date modifiedDate) {
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

        this.userRepository.save(user);

        SignUpVO signUpUserVO = new SignUpVO();
        signUpUserVO.setUserUUID(user.getUUID());

        return signUpUserVO;
    }
}
