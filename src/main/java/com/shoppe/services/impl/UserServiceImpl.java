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
import liquibase.pro.packaged.U;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

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
    public UserVO addAdmin(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String type, String status) {

        Date now = new Date();
        User user = new User();

        user.setFirstName(firstName);
        user.setMiddleInitial(middleInitial);
        user.setLastName(lastName);
        user.setEmailId(emailId);
        user.setUniqueId(uniqueId);
        user.setMobileNo(mobileNo);
        user.setPassword("welcome");
        user.setStatus(status);
        user.setCreatedDate(now);
        user.setModifiedDate(now);

        User admin = this.userRepository.save(user);

        if(null != admin){

            UserVO userVO = new UserVO();
            userVO.setUserUUID(user.getUUID());
            userVO.setFirstName(admin.getFirstName());
            userVO.setMiddleInitial(admin.getMiddleInitial());
            userVO.setLastName(admin.getLastName());
            userVO.setEmailId(admin.getEmailId());
            userVO.setMobileNo(admin.getMobileNo());
            userVO.setUniqueId(admin.getUniqueId());

            return userVO;
        } else {
            logger.error("unable to save the admin information...");
            return null;
        }




    }

    @Override
    public UserVO signUp(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String password, String confirmPassword, String type, String status) {

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
            user.setStatus(status);
            user.setCreatedDate(now);
            user.setModifiedDate(now);

            this.userRepository.save(user);

            Token token = this.tokenService.storeAndGetSignUpVerificationToken(user.getUUID(), UserType.CUSTOMER.toString());
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
    public UserVO getUser(String uuid) {
        User user = this.userRepository.findById(uuid).get();

        if(null != user){
            UserVO userVO = new UserVO();
            userVO.setFirstName(user.getFirstName());
            userVO.setMiddleInitial(user.getMiddleInitial());
            userVO.setLastName(user.getLastName());
            userVO.setEmailId(user.getEmailId());
            userVO.setMobileNo(user.getMobileNo());
            userVO.setUniqueId(user.getUniqueId());
            return userVO;
        } else {
            logger.error(String.format("Unable to find the user for the uuid : %s", uuid));
            return null;
        }
    }

}
