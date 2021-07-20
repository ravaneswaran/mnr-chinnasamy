package com.mnrc.services.impl;

import com.mnrc.enums.UserType;
import com.mnrc.models.User;
import com.mnrc.repositories.UserRepository;
import com.mnrc.services.EmployeeService;
import com.mnrc.services.UserService;
import com.mnrc.ui.forms.Employee;
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
    public Employee addEmployee(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo) {

        User response = this.userService.addUserWithVerifiedStatus(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, UserType.ADMIN.toString());

        if(null != response){
            Employee admin = new Employee();
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
    public List<Employee> listEmployees(){
        List<User> users =  this.userRepository.findAllUsersByType(UserType.ADMIN.toString());
        List<Employee> admins = new ArrayList<>();
        for(User user : users){
            Employee employee = new Employee();
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
    public Employee getEmployee(String uuid) {
        User user = this.userService.getUser(uuid);
        if(null != user && UserType.ADMIN.toString().equals(user.getType())){
            Employee employee = new Employee();
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
