package com.mnrc.core.services;

import com.mnrc.core.entities.User;
import com.mnrc.core.forms.UserForm;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserService {

    @Deprecated
    public User addUserWithVerifiedStatus(
            String firstName,
            String middleInitial,
            String lastName,
            String emailId,
            String uniqueId,
            String mobileNo,
            String type) throws Exception;

    public User addUserWithVerifiedStatus(
            String userRoleId,
            String firstName,
            String middleInitial,
            String lastName,
            String emailId,
            String uniqueId,
            String mobileNo,
            String type) throws Exception;

    public User getUser(String uuid);

    public void lockUser(String uuid);

    public void unLockUser(String uuid);

    public void deleteUser(String uuid);

    public User getUser(String emailId, String password);

    public UserForm getUserForm(String uuid);

    public User getUserByEmailId(String emailId);

    @Deprecated
    public UserForm addUser(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo) throws Exception;

    public UserForm addUser(String userRoleId, String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo) throws Exception;

    public List<UserForm> listUsers();

}