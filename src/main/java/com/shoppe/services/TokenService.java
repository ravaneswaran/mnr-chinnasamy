package com.shoppe.services;

import com.shoppe.models.Token;
import org.springframework.stereotype.Component;

@Component
public interface TokenService {

    public Token getSignUpVerificationToken();

    public Token storeAndGetSignUpVerificationToken(String creatorUUID, String creatorType);

    public Token getSignUpVerificationTokenByUUID(String signUpVerificationTokenUUID);
}
