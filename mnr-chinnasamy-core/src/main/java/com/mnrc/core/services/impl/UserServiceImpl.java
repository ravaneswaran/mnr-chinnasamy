package com.mnrc.core.services.impl;

import com.mnrc.core.enums.UserStatus;
import com.mnrc.core.enums.UserType;
import com.mnrc.core.entities.User;
import com.mnrc.core.entities.UserRole;
import com.mnrc.core.repositories.AddressRepository;
import com.mnrc.core.repositories.UserRepository;
import com.mnrc.core.repositories.UserRoleRepository;
import com.mnrc.core.services.MailService;
import com.mnrc.core.services.TokenService;
import com.mnrc.core.services.UserService;
import com.mnrc.core.forms.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MailService mailService;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserForm addUser(String userRoleId, String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo) throws Exception {

        User response = this.addUserWithVerifiedStatus(userRoleId, firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, UserType.ADMIN.toString());

        if(null != response){
            UserForm userForm = new UserForm();
            userForm.setUserId(response.getUUID());
            userForm.setFirstName(response.getFirstName());
            userForm.setMiddleInitial(response.getMiddleInitial());
            userForm.setLastName(response.getLastName());
            userForm.setEmailId(response.getEmailId());
            userForm.setMobileNo(response.getMobileNo());
            uniqueId = response.getUniqueId();

            if(uniqueId.startsWith("DUMMY-")){
                userForm.setUniqueId("");
            } else {
                userForm.setUniqueId(uniqueId);
            }
            return userForm;
        } else {
            return null;
        }
    }

    @Override
    public User addUserWithVerifiedStatus(String userRoleId, String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String type) throws Exception {

        if(null == userRoleId || "".equals(userRoleId) ){
            throw new Exception("User cannot be created with out a role");
        }

        UserRole userRole = this.userRoleRepository.findById(userRoleId).get();

        Date now = new Date();
        User user = new User();

        user.setFirstName(firstName);
        user.setMiddleInitial(middleInitial);
        user.setLastName(lastName);
        user.setEmailId(emailId);
        if(null != uniqueId && !"".equals(uniqueId.trim())){
            user.setUniqueId(uniqueId);
        }
        user.setMobileNo(mobileNo);
        user.setPassword("welcome");
        user.setType(type);
        user.setRole(userRole);
        user.setStatus(UserStatus.VERIFIED.toString());
        user.setCreatedDate(now);
        user.setModifiedDate(now);

        try {
            User response = this.userRepository.save(user);
            return response;
        } catch (Exception exp) {
            logger.error(exp.getMessage(), exp);
            return null;
        }
    }

    @Override
    public void lockUser(String uuid) {
        Optional<User> optionalUser = this.userRepository.findById(uuid);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            //only user with VERIFIED status can be blocked... in other words only those who can login can be blocked
            //if(UserStatus.VERIFIED.toString().equals(user.getStatus())){

            //}
            user.setStatus(UserStatus.LOCKED.toString());
            this.userRepository.save(user);
        } else {
            logger.error(String.format("UNABLE TO BLOCK : User with id '%s' is not found in the repository", uuid));
        }
    }

    @Override
    public void unLockUser(String uuid) {
        Optional<User> optionalUser = this.userRepository.findById(uuid);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            //only user with BLOCKED status can be unblocked...
            //if (UserStatus.BLOCKED.toString().equals(user.getStatus())) {

            //}
            user.setStatus(UserStatus.VERIFIED.toString());
            this.userRepository.save(user);
        } else {
            logger.error(String.format("UNABLE TO UNBLOCK : User with id '%s' is not found in the repository", uuid));
        }
    }

    @Override
    public void deleteUser(String uuid){
        Optional<User> optionalUser = this.userRepository.findById(uuid);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            this.userRepository.delete(user);
        } else {
            logger.error(String.format("UNABLE TO DELETE : User with id '%s' is not found in the repository", uuid));
        }
    }

    @Override
    public User getUser(String emailId, String password) {
        User user = this.userRepository.findByEmailIdAndPassword(emailId, password);
        if (null != user){
            return user;
        } else {
            logger.error("NO USER FOUND : Unable to find the user for this email id and password combination");
            return null;
        }
    }

    @Override
    public User getUserByEmailId(String emailId) {
        User user = this.userRepository.findByEmailId(emailId);
        if (null != user){
            return user;
        } else {
            logger.error("NO USER FOUND : Unable to find the user registered with this email id");
            return null;
        }
    }



    @Override
    public List<UserForm> listUsers(){
        List<User> users =  this.userRepository.findAllUsersByType(UserType.ADMIN.toString());
        List<UserForm> admins = new ArrayList<>();
        for(User user : users){
            UserForm userForm = new UserForm();
            userForm.setUserId(user.getUUID());
            userForm.setFirstName(user.getFirstName());
            userForm.setMiddleInitial(user.getMiddleInitial());
            userForm.setLastName(user.getLastName());
            userForm.setEmailId(user.getEmailId());
            userForm.setMobileNo(user.getMobileNo());
            userForm.setStatus(user.getStatus());

            File profilePic = new File(String.format("/tmp/%s-profile-pic.png", user.getUUID()));
            if(profilePic.exists()){
                userForm.setProfilePic(String.format("/user/profile/%s-profile-pic.png", user.getUUID()));
            } else {
                userForm.setProfilePic("/images/no-profile-pic.png");
            }

            String uniqueId = user.getUniqueId();

            if(uniqueId.startsWith("DUMMY-")){
                userForm.setUniqueId("");
            } else {
                userForm.setUniqueId(uniqueId);
            }
            admins.add(userForm);
        }
        return admins;
    }

    @Override
    public User getUser(String uuid) {
        Optional<User> optionalUser =  this.userRepository.findById(uuid);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(user.getUniqueId().startsWith("DUMMY-")){
                user.setUniqueId("");
            }
            return user;
        } else {
            logger.error(String.format("Unable to find the user for the id '%s'", uuid));
            return null;
        }
    }

    @Override
    public UserForm getUserForm(String uuid) {
        User user = this.getUser(uuid);
        if(null != user && UserType.ADMIN.toString().equals(user.getType())){
            UserForm userForm = new UserForm();
            userForm.setUserId(user.getUUID());
            userForm.setFirstName(user.getFirstName());
            userForm.setMiddleInitial(user.getMiddleInitial());
            userForm.setEmailId(user.getEmailId());
            userForm.setMobileNo(user.getMobileNo());
            userForm.setStatus(user.getStatus());

            String uniqueId = user.getUniqueId();

            if(uniqueId.startsWith("DUMMY-")){
                userForm.setUniqueId("");
            } else {
                userForm.setUniqueId(uniqueId);
            }

            File profilePic = new File(String.format("/tmp/%s-profile-pic.png", user.getUUID()));
            if(profilePic.exists()){
                userForm.setProfilePic(String.format("/user/profile/%s-profile-pic.png", user.getUUID()));
            } else {
                userForm.setProfilePic("/images/no-profile-pic.png");
            }

            return userForm;
        } else {
            return null;
        }
    }
}
