package com.mnrc.administration.services.impl;

import com.mnrc.administration.models.UserRole;
import com.mnrc.administration.repositories.UserRoleRepository;
import com.mnrc.administration.services.UserRoleService;
import com.mnrc.administration.ui.forms.UserRoleForm;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
        userRole.setModifiedDate(now);

        try {
            UserRole response = this.userRoleRepository.save(userRole);
            UserRoleForm userRoleForm = new UserRoleForm();
            userRoleForm.setUserRoleId(response.getUUID());

            return userRoleForm;
        } catch (ConstraintViolationException cve){
            throw new Exception("User Role name already exists...");
        }
    }

    @Override
    public void deleteUserRole(String userRoleId) throws Exception {
        if(null == userRoleId){
            throw new Exception("User role id cannot be null...");
        }

        if("".equals(userRoleId)){
            throw new Exception("User role id cannot be a empty string...");
        }

        this.userRoleRepository.deleteById(userRoleId);
    }

    @Override
    public UserRoleForm editUserRole(String userRoleId, String name, String userName) throws Exception {
        if(null == userRoleId){
            throw new Exception("User role id cannot be null...");
        }

        if("".equals(userRoleId)){
            throw new Exception("User role id cannot be a empty string...");
        }

        UserRole userRole = this.userRoleRepository.findById(userRoleId).get();

        if(null != userRole){

            userRole.setName(name.toUpperCase());
            userRole.setModifiedBy(userName);
            userRole.setModifiedDate(new Date());

            try{
                userRole = this.userRoleRepository.save(userRole);
                UserRoleForm userRoleForm = new UserRoleForm();
                userRoleForm.setUserRoleId(userRole.getUUID());
                userRoleForm.setUserRoleName(userRole.getName());
                userRoleForm.setNoOfUsers(0);

                return userRoleForm;
            } catch (ConstraintViolationException cve){
                throw new Exception("User Role name already exists...");
            }
        } else {
            throw new Exception("Invalid user role id...");
        }
    }

    @Override
    public UserRoleForm getUserRole(String userRoleId) throws Exception {

        if(null == userRoleId){
            throw new Exception("User role id cannot be null...");
        }

        if("".equals(userRoleId)){
            throw new Exception("User role id cannot be a empty string...");
        }

        UserRole userRole = this.userRoleRepository.findById(userRoleId).get();

        if(null != userRole){
            UserRoleForm userRoleForm = new UserRoleForm();
            userRoleForm.setUserRoleId(userRole.getUUID());
            userRoleForm.setUserRoleName(userRole.getName());
            userRoleForm.setNoOfUsers(0);

            return userRoleForm;
        } else {
            throw new Exception("Invalid user role id...");
        }
    }

    @Override
    public List<UserRoleForm> getUserRoles() {
        List<UserRoleForm> userRoleForms = new ArrayList<>();
        Iterable<UserRole> userRoles = this.userRoleRepository.findAll();
        for(UserRole userRole : userRoles){
            UserRoleForm userRoleForm = new UserRoleForm();
            userRoleForm.setUserRoleName(userRole.getName());
            userRoleForm.setUserRoleId(userRole.getUUID());
            userRoleForms.add(userRoleForm);
        }
        return userRoleForms;
    }
}
