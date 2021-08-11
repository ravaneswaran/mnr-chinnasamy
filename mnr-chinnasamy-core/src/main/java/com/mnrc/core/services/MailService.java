package com.mnrc.core.services;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public interface MailService {

    public int sendUserVerificationMail(String tokenId, String firstName, String middleInitial, String lastName, String emailId);

    public int sendForgotPasswordMail(String firstName, String middleInitial, String lastName, String password, String emailId, String subject);

}
