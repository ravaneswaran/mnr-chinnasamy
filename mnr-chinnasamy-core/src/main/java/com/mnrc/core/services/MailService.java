package com.mnrc.core.services;

public interface MailService {

    public int sendUserVerificationMail(String tokenId, String firstName, String middleInitial, String lastName, String emailId);

    public int sendForgotPasswordMail(String firstName, String middleInitial, String lastName, String password, String emailId, String subject);

}
