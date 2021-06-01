package com.webshoppe.services;

import com.webshoppe.enums.TokenType;
import com.webshoppe.models.Token;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenServiceTest {

    @Autowired
    private TokenService tokenService;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.tokenService);
    }

    @Test
    public void testSignUpVerificationToken(){
        Token token = this.tokenService.getSignUpVerificationToken();

        Assert.assertEquals(TokenType.SIGNUP_VERIFICATION_TOKEN.toString(), token.getType());
        Assert.assertEquals(24, token.getExpiryTimeInHours());
    }

}
