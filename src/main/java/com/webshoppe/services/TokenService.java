package com.webshoppe.services;

import com.webshoppe.models.Token;
import org.springframework.stereotype.Component;

@Component
public interface TokenService {

    public Token getToken(String type, int expiryTimeInHours);

    public Token getSignUpVerificationToken();
}
