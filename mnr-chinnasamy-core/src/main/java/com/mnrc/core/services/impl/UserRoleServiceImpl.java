package com.mnrc.core.services.impl;

import com.mnrc.core.entities.UserRole;
import com.mnrc.core.repositories.UserRoleRepository;
import com.mnrc.core.services.UserRoleService;
import com.mnrc.core.forms.UserRoleForm;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
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
            userRoleForm.setRoleId(response.getUUID());

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
                userRoleForm.setRoleId(userRole.getUUID());
                userRoleForm.setRoleName(userRole.getName());
                userRoleForm.setCanAccessAdministrationApp(userRole.getCanAccessAdministrationApp());
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
            userRoleForm.setRoleId(userRole.getUUID());
            userRoleForm.setRoleName(userRole.getName());
            userRoleForm.setCanAccessAdministrationApp(userRole.getCanAccessAdministrationApp());
            userRoleForm.setNoOfUsers(0);

            return userRoleForm;
        } else {
            throw new Exception("Invalid user role id...");
        }
    }

    @Override
    public List<UserRoleForm> getUserRoles() {
        List<UserRoleForm> userRoleForms = new ArrayList<>();
        Iterable<UserRole> userRoles = this.userRoleRepository.findAllExcludingAlmightyRole();
        for(UserRole userRole : userRoles){
            UserRoleForm userRoleForm = new UserRoleForm();
            userRoleForm.setRoleName(userRole.getName());
            userRoleForm.setRoleId(userRole.getUUID());
            userRoleForm.setCanAccessAdministrationApp(userRole.getCanAccessAdministrationApp());
            userRoleForms.add(userRoleForm);
        }
        return userRoleForms;
    }

    @Override
    public String toggleCanAccessAdministrationApp(String userRoleId, boolean canAccessAdministrationApp) throws Exception {
        if(null == userRoleId){
            throw new Exception("User role id cannot be null...");
        }

        if("".equals(userRoleId)){
            throw new Exception("User role id cannot be a empty string...");
        }

        Optional<UserRole> optionalUserRole = this.userRoleRepository.findById(userRoleId);

        if(!optionalUserRole.isPresent()){
            throw new Exception(String.format("User role is not found for the id (%s)", userRoleId));
        }

        UserRole userRole = optionalUserRole.get();
        int toggleValue = 0;
        if(canAccessAdministrationApp){
            toggleValue = 1;
            userRole.setCanAccessAdministrationApp(toggleValue);
        } else {
            userRole.setCanAccessAdministrationApp(toggleValue);
        }
        this.userRoleRepository.save(userRole);

        return String.valueOf(toggleValue);
    }
}
