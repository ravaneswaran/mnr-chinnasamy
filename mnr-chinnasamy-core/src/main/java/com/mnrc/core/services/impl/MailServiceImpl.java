package com.mnrc.core.services.impl;

import com.mnrc.core.services.MailService;
import com.mnrc.core.utils.MailerUtil;
import com.mnrc.core.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;

@Service
public class MailServiceImpl implements MailService {

    Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private MailerUtil mailerUtil;

    @Autowired
    private StringUtil stringUtil;

    @Value("${mail.noreply}")
    private String noReplyMailId;

    @Value("${mail.subject.signup.verification}")
    private String signUpVerificationSubject;


    @Override
    public int sendUserVerificationMail(String tokenId, String firstName, String middleInitial, String lastName, String emailId) {
        String mailContent = null;
        int result = -1;
        try {
            mailContent = this.stringUtil.getResourceAsString("/mail-messages/signup-verification-mail.html");
        } catch (IOException e) {
            this.logger.error(e.getMessage(), e);
        }

        if(null != mailContent){
            mailContent = String.format(mailContent, firstName, middleInitial, lastName, tokenId);
            try {
                this.mailerUtil.sendMailMessage(this.noReplyMailId, emailId, this.signUpVerificationSubject, mailContent);
                result = 0;
            } catch (MessagingException e) {
                this.logger.error(e.getMessage(), e);
            }
        }

        return result;
    }

    @Override
    public int sendForgotPasswordMail(String firstName, String middleInitial, String lastName, String password, String emailId, String subject) {
        String mailContent = null;
        int result = -1;
        try {
            mailContent = this.stringUtil.getResourceAsString("/mail-messages/forgot-password-mail.html");
        } catch (IOException e) {
            this.logger.error(e.getMessage(), e);
        }

        if(null != mailContent){
            mailContent = String.format(mailContent, firstName, middleInitial, lastName, password);
            try {
                this.mailerUtil.sendMailMessage(this.noReplyMailId, emailId, subject, mailContent);
                result = 0;
            } catch (MessagingException e) {
                this.logger.error(e.getMessage(), e);
            }
        }

        return result;
    }
}
