package com.mnrc.services.impl;

import com.mnrc.models.UserRole;
import com.mnrc.repositories.UserRoleRepository;
import com.mnrc.services.UserRoleService;
import com.mnrc.ui.forms.UserRoleForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;

@Component
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Override
    public UserRoleForm addUserRole(String name, String userName) throws Exception {
        if(null == name){
            throw new Exception("User Role name cannot be null...");
        }

        if("".equals(name)){
            throw new Exception("User Role name cannot be a empty string...");
        }

        if(null == userName){
            throw new Exception("User name cannot be null...");
        }

        if("".equals(userName)){
            throw new Exception("User name cannot be a empty string...");
        }

        if(name.contains(" ")){
            throw new Exception("User Role name should not have any white space...");
        }

        String uuid = UUID.randomUUID().toString();
        Date now = new Date();

        UserRole userRole = new UserRole();
        userRole.setUUID(uuid);
        userRole.setName(name.toUpperCase());
        userRole.setCreatedBy(userName);
        userRole.setModifiedBy(userName);
        userRole.setCreatedDate(now);

        UserRole response = this.userRoleRepository.save(userRole);

        if(null != response){
            UserRoleForm userRoleForm = new UserRoleForm();
            userRoleForm.setUserRoleId(response.getUUID());

            return userRoleForm;
        }

        return null;
    }

    @Override
    public void deleteUserRole(String userRoleId) {

    }

    @Override
    public void editUserRole(String userRoleId, String name, String userName) {

    }

    @Override
    public void getUserRole(String userRoleId) {

    }
}
