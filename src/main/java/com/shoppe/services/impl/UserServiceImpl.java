package com.shoppe.services.impl;

import com.shoppe.enums.UserStatus;
import com.shoppe.enums.UserType;
import com.shoppe.models.Token;
import com.shoppe.models.User;
import com.shoppe.repositories.UserRepository;
import com.shoppe.services.MailService;
import com.shoppe.services.TokenService;
import com.shoppe.services.UserService;
import com.shoppe.services.vo.SignUpVO;
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
    public SignUpVO addAdmin(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String status) {

        SignUpVO signUpUserVO = new SignUpVO();
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

        this.userRepository.save(user);
        Token token = this.tokenService.storeAndGetSignUpVerificationToken(user.getUUID(), UserType.ADMIN.toString());
        this.mailService.sendUserVerificationMail(token.getUUID(), firstName, middleInitial, lastName, emailId);

        return signUpUserVO;
    }

    @Override
    public SignUpVO verifySignedUpUser(String signUpVerificationTokenUUID) {
        SignUpVO signUpVO = new SignUpVO();
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
                    signUpVO.setErrorMessage("Token expired");
                }
            } else {
                signUpVO.setErrorMessage(String.format("Token id '%s' found to be invalid", signUpVerificationTokenUUID));
            }
        } else {
            signUpVO.setErrorMessage(String.format("Token id '%s' found to be invalid", signUpVerificationTokenUUID));
        }

        return signUpVO;
    }
}
