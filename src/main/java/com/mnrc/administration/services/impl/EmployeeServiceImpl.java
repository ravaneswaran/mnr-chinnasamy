package com.mnrc.administration.services.impl;

import com.mnrc.administration.enums.UserType;
import com.mnrc.administration.models.User;
import com.mnrc.administration.repositories.UserRepository;
import com.mnrc.administration.services.EmployeeService;
import com.mnrc.administration.services.UserService;
import com.mnrc.administration.ui.forms.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserForm addEmployee(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo) {

        User response = this.userService.addUserWithVerifiedStatus(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, UserType.ADMIN.toString());

        if(null != response){
            UserForm admin = new UserForm();
            admin.setEmployeeId(response.getUUID());
            admin.setFirstName(response.getFirstName());
            admin.setMiddleInitial(response.getMiddleInitial());
            admin.setLastName(response.getLastName());
            admin.setEmailId(response.getEmailId());
            admin.setMobileNo(response.getMobileNo());
            uniqueId = response.getUniqueId();

            if(uniqueId.startsWith("DUMMY-")){
                admin.setUniqueId("");
            } else {
                admin.setUniqueId(uniqueId);
            }
            return admin;
        } else {
            return null;
        }
    }

    @Override
    public List<UserForm> listEmployees(){
        List<User> users =  this.userRepository.findAllUsersByType(UserType.ADMIN.toString());
        List<UserForm> admins = new ArrayList<>();
        for(User user : users){
            UserForm employee = new UserForm();
            employee.setEmployeeId(user.getUUID());
            employee.setFirstName(user.getFirstName());
            employee.setMiddleInitial(user.getMiddleInitial());
            employee.setLastName(user.getLastName());
            employee.setEmailId(user.getEmailId());
            employee.setMobileNo(user.getMobileNo());
            employee.setStatus(user.getStatus());

            File profilePic = new File(String.format("/tmp/%s-profile-pic.png", user.getUUID()));
            if(profilePic.exists()){
                employee.setProfilePic(String.format("/employee/profile/%s-profile-pic.png", user.getUUID()));
            } else {
                employee.setProfilePic("/images/no-profile-pic.png");
            }

            String uniqueId = user.getUniqueId();

            if(uniqueId.startsWith("DUMMY-")){
                employee.setUniqueId("");
            } else {
                employee.setUniqueId(uniqueId);
            }
            admins.add(employee);
        }

        return admins;
    }

    @Override
    public UserForm getEmployee(String uuid) {
        User user = this.userService.getUser(uuid);
        if(null != user && UserType.ADMIN.toString().equals(user.getType())){
            UserForm employee = new UserForm();
            employee.setEmployeeId(user.getUUID());
            employee.setFirstName(user.getFirstName());
            employee.setMiddleInitial(user.getMiddleInitial());
            employee.setEmailId(user.getEmailId());
            employee.setMobileNo(user.getMobileNo());
            employee.setStatus(user.getStatus());

            String uniqueId = user.getUniqueId();

            if(uniqueId.startsWith("DUMMY-")){
                employee.setUniqueId("");
            } else {
                employee.setUniqueId(uniqueId);
            }

            File profilePic = new File(String.format("/tmp/%s-profile-pic.png", user.getUUID()));
            if(profilePic.exists()){
                employee.setProfilePic(String.format("/employee/profile/%s-profile-pic.png", user.getUUID()));
            } else {
                employee.setProfilePic("/images/no-profile-pic.png");
            }

            return employee;
        } else {
            return null;
        }
    }

    @Override
    public void lockEmployee(String uuid) {
        this.userService.lockUser(uuid);
    }

    @Override
    public void unLockEmployee(String uuid) {
        this.userService.unLockUser(uuid);
    }

    @Override
    public void deleteEmployee(String uuid) {
        this.userService.deleteUser(uuid);
    }
}
