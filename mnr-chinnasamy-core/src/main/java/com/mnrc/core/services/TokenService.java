package com.mnrc.core.services;

import com.mnrc.core.entities.Token;

public interface TokenService {

    public Token getSignUpVerificationToken();

    public Token storeAndGetSignUpVerificationToken(String creatorUUID, String creatorType);

    public Token getSignUpVerificationTokenByUUID(String signUpVerificationTokenUUID);
}
