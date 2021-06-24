package com.shoppe.services;

import com.shoppe.enums.UserStatus;
import com.shoppe.enums.UserType;
import com.shoppe.models.Token;
import com.shoppe.models.User;
import com.shoppe.repositories.TokenRepository;
import com.shoppe.repositories.UserRepository;
import com.shoppe.services.vo.UserVO;
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
    public void testAddAdmin(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String status = UserStatus.SIGN_UP_VERIFICATION_PENDING.toString();
        String type = UserType.ADMIN.toString();

        UserVO userVO = this.userService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, type, status);

        Assert.assertNotNull(userVO.getUserUUID());
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
        String type = UserType.CUSTOMER.toString();
        String status = UserStatus.SIGN_UP_VERIFICATION_PENDING.toString();

        UserVO userVO = this.userService.signUp(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, password, password, type, status);
        User user = this.userRepository.findById(userVO.getUserUUID()).get();

        Assert.assertEquals(UserStatus.SIGN_UP_VERIFICATION_PENDING.toString(), user.getStatus());
    }

    @Test
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
        String type = UserType.CUSTOMER.toString();
        String status = UserStatus.SIGN_UP_VERIFICATION_PENDING.toString();

        UserVO userVO = this.userService.signUp(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, password, password, type, status);
        Token token = tokenRepository.findSignUpVerificationTokenByCreatorUUID(userVO.getUserUUID());
        this.userService.verifySignedUpUser(token.getUUID());
        String userUUID = userVO.getUserUUID();

        User user = this.userRepository.findById(userUUID).get();
        Assert.assertEquals(UserStatus.VERIFIED.toString(), user.getStatus());
    }

}
