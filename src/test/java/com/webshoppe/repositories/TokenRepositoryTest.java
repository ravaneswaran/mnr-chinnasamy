package com.webshoppe.repositories;

import com.webshoppe.models.Token;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TokenRepositoryTest {

    @Autowired
    private TokenRepository tokenRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.tokenRepository);
    }

    @Test
    public void testSignUpVerificationTokenSave(){
        String uuid = UUID.randomUUID().toString();
        Date newDate = new Date();

        Token signUpVerificationToken = new Token();
        signUpVerificationToken.setUUID(uuid);
        signUpVerificationToken.setType("SIGNUP-VERIFICATION-TOKEN");
        signUpVerificationToken.setExpiryTimeInHours(24);
        signUpVerificationToken.setCreatedDate(newDate);
        signUpVerificationToken.setModifiedDate(newDate);

        this.tokenRepository.save(signUpVerificationToken);

        Token result = this.tokenRepository.findById(uuid).get();

        Assert.assertNotNull(result);
        Assert.assertEquals(uuid, result.getUUID());
    }
}
