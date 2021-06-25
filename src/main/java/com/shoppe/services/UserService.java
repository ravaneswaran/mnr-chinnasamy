package com.shoppe.services;

import com.shoppe.services.vo.UserVO;
import com.shoppe.ui.forms.AdminForm;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    public AdminForm addAdmin(
            String firstName,
            String middleInitial,
            String lastName,
            String emailId,
            String uniqueId,
            String mobileNo,
            String type,
            String status);

    public List<AdminForm> listAdmins();

    public UserVO signUp(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String password, String confirmPassword, String type, String status);

    public UserVO verifySignedUpUser(String signUpVerificationTokenUUID);

    public UserVO getUser(String uuid);

    public void blockUser(String uuid);

    public void unblockUser(String uuid);

    public void deleteUser(String uuid);
}
