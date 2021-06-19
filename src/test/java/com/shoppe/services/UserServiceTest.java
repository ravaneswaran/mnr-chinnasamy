package com.shoppe.services;

import com.shoppe.enums.UserStatus;
import com.shoppe.repositories.TokenRepository;
import com.shoppe.repositories.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.userService);
    }

    @Test
    public void testSignUp(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = String.format("password-%s", randomNumberString);
        String status = UserStatus.SIGN_UP_VERIFICATION_PENDING.toString();

        int result = this.userService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, status);

        Assert.assertEquals(UserStatus.SIGN_UP_VERIFICATION_PENDING.toString(), result);
    }

    /*@Test
    public void testVerifySignedUpUser(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = String.format("password-%s", randomNumberString);
        String status = UserStatus.SIGN_UP_VERIFICATION_PENDING.toString();

        SignUpVO signUpVO = this.userService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, status);
        Token token = tokenRepository.findSignUpVerificationTokenByCreatorUUID(signUpVO.getUserUUID());
        this.userService.verifySignedUpUser(token.getUUID());
        String userUUID = signUpVO.getUserUUID();

        User user = this.userRepository.findById(userUUID).get();
        Assert.assertEquals(UserStatus.VERIFIED.toString(), user.getStatus());
    }*/

}
