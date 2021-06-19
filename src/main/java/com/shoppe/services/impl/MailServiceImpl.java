package com.shoppe.services.impl;

import com.shoppe.services.MailService;
import com.shoppe.utils.MailerUtil;
import com.shoppe.utils.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import java.io.IOException;

@Component
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
            mailContent = this.stringUtil.getResourceAsString("mail-messages/signup-verification-mail.html");
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
}
