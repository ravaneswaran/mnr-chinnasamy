package com.mnrc.administration.services;

import com.mnrc.administration.models.Token;
import org.springframework.stereotype.Component;

@Component
public interface TokenService {

    public Token getSignUpVerificationToken();

    public Token storeAndGetSignUpVerificationToken(String creatorUUID, String creatorType);

    public Token getSignUpVerificationTokenByUUID(String signUpVerificationTokenUUID);
}
