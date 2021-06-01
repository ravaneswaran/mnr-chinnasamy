package com.webshoppe.services;

import com.webshoppe.valueobj.SignUpVO;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface UserService {

    public SignUpVO signUp(
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
