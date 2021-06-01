package com.webshoppe.services.impl;

import com.webshoppe.enums.TokenType;
import com.webshoppe.models.Token;
import com.webshoppe.services.TokenService;
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
