package com.shoppe.services.impl;

import com.shoppe.enums.UserStatus;
import com.shoppe.enums.UserType;
import com.shoppe.models.Token;
import com.shoppe.models.User;
import com.shoppe.repositories.UserRepository;
import com.shoppe.services.MailService;
import com.shoppe.services.TokenService;
import com.shoppe.services.UserService;
import com.shoppe.services.vo.UserVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
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
        uniqueId = (null != uniqueId && !"".equals(uniqueId.trim())) ? uniqueId : String.format("DUMMY-%s", String.valueOf(new Date().getTime()));
        user.setUniqueId(uniqueId);
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
    public UserVO signUpUser(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String password, String confirmPassword) {

        UserVO userVO = new UserVO();
        if(password.equals(confirmPassword)){
            Date now = new Date();
            User user = new User();

            user.setFirstName(firstName);
            user.setMiddleInitial(middleInitial);
            user.setLastName(lastName);
            user.setEmailId(emailId);
            user.setUniqueId(uniqueId);
            user.setMobileNo(mobileNo);
            user.setPassword(password);
            user.setType(UserType.PERSON.toString());
            user.setStatus(UserStatus.SIGN_UP_VERIFICATION_PENDING.toString());
            user.setCreatedDate(now);
            user.setModifiedDate(now);

            this.userRepository.save(user);

            Token token = this.tokenService.storeAndGetSignUpVerificationToken(user.getUUID(), UserType.PERSON.toString());
            this.mailService.sendUserVerificationMail(token.getUUID(), firstName, middleInitial, lastName, emailId);
            userVO.setUserUUID(user.getUUID());
        } else {
            userVO.setErrorMessage("Password and Confirm Password do not match.");
        }
        return userVO;
    }

    @Override
    public UserVO verifySignedUpUser(String signUpVerificationTokenUUID) {
        UserVO userVO = new UserVO();
        Token token = this.tokenService.getSignUpVerificationTokenByUUID(signUpVerificationTokenUUID);
        if(null != token) {
            User user = this.userRepository.findById(token.getCreatorUUID()).get();
            if (null != user) {
                Date now = new Date();
                Date tokenCreatedDate = token.getCreatedDate();
                Date expiryTime = new Date(tokenCreatedDate.getTime() + 24*60*60*1000);

                if(now.before(expiryTime)){
                    user.setStatus(UserStatus.VERIFIED.toString());
                    user.setModifiedDate(new Date());
                    this.userRepository.save(user);
                } else {
                    userVO.setErrorMessage("Token expired");
                }
            } else {
                userVO.setErrorMessage(String.format("Token id '%s' found to be invalid", signUpVerificationTokenUUID));
            }
        } else {
            userVO.setErrorMessage(String.format("Token id '%s' found to be invalid", signUpVerificationTokenUUID));
        }
        return userVO;
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
    public void blockUser(String uuid) {
        Optional<User> optionalUser = this.userRepository.findById(uuid);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            //only user with VERIFIED status can be blocked... in other words only those who can login can be blocked
            if(UserStatus.VERIFIED.toString().equals(user.getStatus())){
                user.setStatus(UserStatus.BLOCKED.toString());
            }
            this.userRepository.save(user);
        } else {
            logger.error(String.format("UNABLE TO BLOCK : User with id '%s' is not found in the repository", uuid));
        }
    }

    @Override
    public void unblockUser(String uuid) {
        Optional<User> optionalUser = this.userRepository.findById(uuid);
        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            //only user with BLOCKED status can be unblocked...
            if (UserStatus.BLOCKED.toString().equals(user.getStatus())) {
                user.setStatus(UserStatus.VERIFIED.toString());
            }
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
}
