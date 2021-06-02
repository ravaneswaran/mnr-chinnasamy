package com.webshoppe.services.impl;

import com.webshoppe.enums.UserStatus;
import com.webshoppe.enums.UserType;
import com.webshoppe.models.Token;
import com.webshoppe.models.User;
import com.webshoppe.repositories.UserRepository;
import com.webshoppe.services.TokenService;
import com.webshoppe.services.UserService;
import com.webshoppe.utils.MailerUtil;
import com.webshoppe.utils.StringUtil;
import com.webshoppe.valueobj.SignUpVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private MailerUtil mailerUtil;

    @Autowired
    private StringUtil stringUtil;

    @Value("${mail.noreply}")
    private String noReplyMailId;

    @Value("${mail.subject.signup.verification}")
    private String signUpVerificationSubject;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public SignUpVO signUp(String firstName, String middleInitial, String lastName, String emailId, String uniqueId, String mobileNo, String password, String confirmPassword, String status) {

        SignUpVO signUpUserVO = new SignUpVO();
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

            String mailContent = null;
            try {
                mailContent = this.stringUtil.getResourceAsString("mail-messages/signup-verification-mail.html");
            } catch (IOException e) {
                this.logger.error(e.getMessage(), e);
                signUpUserVO.setErrorMessage(e.getMessage());
            }

            if(null != mailContent){
                mailContent = String.format(mailContent, firstName, middleInitial, lastName, token.getUUID());
                try {
                    this.mailerUtil.sendMailMessage(this.noReplyMailId, emailId, this.signUpVerificationSubject, mailContent);
                    signUpUserVO.setUserUUID(user.getUUID());
                } catch (MessagingException e) {
                    this.logger.error(e.getMessage(), e);
                    signUpUserVO.setErrorMessage(e.getMessage());
                }
            }
        } else {
            signUpUserVO.setErrorMessage("Password and Confirm Password do not match.");
        }

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
