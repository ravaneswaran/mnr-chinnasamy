package com.shoppe.services;

import com.shoppe.services.vo.SignUpVO;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    public int addAdmin(
            String firstName,
            String middleInitial,
            String lastName,
            String emailId,
            String uniqueId,
            String mobileNo,
            String status);

    public SignUpVO signUp(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String password, String confirmPassword, String status);

    public SignUpVO verifySignedUpUser(String signUpVerificationTokenUUID);
}
