package com.mnrc.administration.services.impl;

import com.mnrc.administration.enums.UserStatus;
import com.mnrc.administration.enums.UserType;
import com.mnrc.administration.models.Token;
import com.mnrc.administration.models.User;
import com.mnrc.administration.repositories.UserRepository;
import com.mnrc.administration.services.MailService;
import com.mnrc.administration.services.TokenService;
import com.mnrc.administration.services.UserService;
import com.mnrc.administration.ui.forms.UserForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MailService mailService;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User addUserWithVerifiedStatus(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String type) {
        Date now = new Date();
        User user = new User();

        user.setFirstName(firstName);
        user.setMiddleInitial(middleInitial);
        user.setLastName(lastName);
        user.setEmailId(emailId);
        String uniqueIdAlias = (null != uniqueId && !"".equals(uniqueId.trim())) ? uniqueId : String.format("DUMMY-%s", String.valueOf(new Date().getTime()));
        user.setUniqueId(uniqueIdAlias);
        user.setMobileNo(mobileNo);
        user.setPassword("welcome");
        user.setType(type);
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
    public void deleteUser(String uuid) {
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
    public UserForm addUser(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo) {

        User response = this.addUserWithVerifiedStatus(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, UserType.ADMIN.toString());

        if(null != response){
            UserForm admin = new UserForm();
            admin.setUserId(response.getUUID());
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
    public List<UserForm> listUsers(){
        List<User> users =  this.userRepository.findAllUsersByType(UserType.ADMIN.toString());
        List<UserForm> admins = new ArrayList<>();
        for(User user : users){
            UserForm employee = new UserForm();
            employee.setUserId(user.getUUID());
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
            UserForm employee = new UserForm();
            employee.setUserId(user.getUUID());
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
}
