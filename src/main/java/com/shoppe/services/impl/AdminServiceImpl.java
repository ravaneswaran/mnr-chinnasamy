package com.shoppe.services.impl;

import com.shoppe.enums.UserType;
import com.shoppe.models.User;
import com.shoppe.repositories.UserRepository;
import com.shoppe.services.AdminService;
import com.shoppe.services.UserService;
import com.shoppe.ui.forms.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Admin addAdmin(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo) {

        User response = this.userService.addUserWithVerifiedStatus(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, UserType.ADMIN.toString());

        if(null != response){
            Admin admin = new Admin();
            admin.setAdminId(response.getUUID());
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
    public List<Admin> listAdmins(){
        List<User> users =  this.userRepository.findAllUsersByType(UserType.ADMIN.toString());
        List<Admin> admins = new ArrayList<>();
        for(User user : users){
            Admin admin = new Admin();
            admin.setAdminId(user.getUUID());
            admin.setFirstName(user.getFirstName());
            admin.setMiddleInitial(user.getMiddleInitial());
            admin.setLastName(user.getLastName());
            admin.setEmailId(user.getEmailId());
            admin.setMobileNo(user.getMobileNo());
            String uniqueId = user.getUniqueId();

            if(uniqueId.startsWith("DUMMY-")){
                admin.setUniqueId("");
            } else {
                admin.setUniqueId(uniqueId);
            }
            admins.add(admin);
        }

        return admins;
    }

    @Override
    public Admin getAdmin(String uuid) {
        User user = this.userService.getUser(uuid);
        if(null != user && UserType.ADMIN.toString().equals(user.getType())){
            Admin admin = new Admin();
            admin.setAdminId(user.getUUID());
            admin.setFirstName(user.getFirstName());
            admin.setMiddleInitial(user.getMiddleInitial());
            admin.setEmailId(user.getEmailId());
            admin.setUniqueId(user.getUniqueId());
            admin.setMobileNo(user.getMobileNo());
            return admin;
        } else {
            return null;
        }
    }

    @Override
    public void blockAdmin(String uuid) {
        this.userService.blockUser(uuid);
    }

    @Override
    public void unblockAdmin(String uuid) {
        this.userService.unblockUser(uuid);
    }

    @Override
    public void deleteAdmin(String uuid) {
        this.userService.deleteUser(uuid);
    }
}
