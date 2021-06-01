package com.store.core.services.impl;

import com.store.core.enums.TokenType;
import com.store.core.models.Token;
import com.store.core.services.TokenService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenServiceImpl implements TokenService {


    @Override
    public Token getToken(String type, int expiryTimeInHours) {
        Token token = new Token();
        Date newDate = new Date();

        token.setType(type);
        token.setExpiryTimeInHours(expiryTimeInHours);
        token.setCreatedDate(newDate);
        token.setModifiedDate(newDate);

        return token;
    }

    @Override
    public Token getSignUpVerificationToken() {
        return this.getToken(TokenType.SIGNUP_VERIFICATION_TOKEN.toString(), 24);
    }
}
