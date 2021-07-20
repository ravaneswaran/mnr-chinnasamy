package com.mnrc.administration.services;

import com.mnrc.administration.models.User;
import com.mnrc.administration.services.vo.UserVO;
import org.springframework.stereotype.Component;

@Component
public interface UserService {

    public User addUserWithVerifiedStatus(
            String firstName,
            String middleInitial,
            String lastName,
            String emailId,
            String uniqueId,
            String mobileNo,
            String type);

    public UserVO signUpUser(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String password, String confirmPassword);

    public UserVO verifySignedUpUser(String signUpVerificationTokenUUID);

    public User getUser(String uuid);

    public void lockUser(String uuid);

    public void unLockUser(String uuid);

    public void deleteUser(String uuid);

    public User getUser(String emailId, String password);

    public User getUserByEmailId(String emailId);
}
