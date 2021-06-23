package com.shoppe.services;

import com.shoppe.services.vo.UserVO;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    public UserVO addAdmin(
            String firstName,
            String middleInitial,
            String lastName,
            String emailId,
            String uniqueId,
            String mobileNo,
            String status);

    public UserVO signUp(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String password, String confirmPassword, String status);

    public UserVO verifySignedUpUser(String signUpVerificationTokenUUID);

}
