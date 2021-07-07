package com.mnrc.services;

import com.mnrc.enums.UserStatus;
import com.mnrc.enums.UserType;
import com.mnrc.models.Token;
import com.mnrc.models.User;
import com.mnrc.repositories.TokenRepository;
import com.mnrc.repositories.UserRepository;
import com.mnrc.services.vo.UserVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
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
    public void testAddUserWithVerifiedStatus() {
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String status = UserStatus.VERIFIED.toString();
        String type = UserType.ADMIN.toString();

        User user = this.userService.addUserWithVerifiedStatus(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, type);

        Assert.assertNotNull(user.getUUID());
    }

    @Test
    public void testSignUp(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = String.format("password-%s", randomNumberString);
        String type = UserType.PERSON.toString();
        String status = UserStatus.SIGN_UP_VERIFICATION_PENDING.toString();

        UserVO userVO = this.userService.signUpUser(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, password, password);
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
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String password = String.format("password-%s", randomNumberString);
        String type = UserType.PERSON.toString();
        String status = UserStatus.SIGN_UP_VERIFICATION_PENDING.toString();

        UserVO userVO = this.userService.signUpUser(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, password, password);
        Token token = tokenRepository.findSignUpVerificationTokenByCreatorUUID(userVO.getUserUUID());
        this.userService.verifySignedUpUser(token.getUUID());
        String userUUID = userVO.getUserUUID();

        User user = this.userRepository.findById(userUUID).get();
        Assert.assertEquals(UserStatus.VERIFIED.toString(), user.getStatus());
    }


    @Test
    public void testBlockUser(){

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String type = UserType.ADMIN.toString();
        User user = this.userService.addUserWithVerifiedStatus(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, type);

        this.userService.blockUser(user.getUUID());

        User response = this.userRepository.findById(user.getUUID()).get();
        Assert.assertEquals(UserStatus.BLOCKED.toString(), response.getStatus());
    }

    @Test
    public void testUnBlockUser(){

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String status = UserStatus.VERIFIED.toString();
        String type = UserType.ADMIN.toString();
        User user = this.userService.addUserWithVerifiedStatus(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, type);
        this.userService.blockUser(user.getUUID());

        this.userService.unblockUser(user.getUUID());

        User response = this.userRepository.findById(user.getUUID()).get();
        Assert.assertEquals(UserStatus.VERIFIED.toString(), user.getStatus());
    }

    @Test
    public void testDeleteUser(){

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String status = UserStatus.VERIFIED.toString();
        String type = UserType.ADMIN.toString();
        User user = this.userService.addUserWithVerifiedStatus(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, type);

        this.userService.deleteUser(user.getUUID());

        Optional<User> optionalUser = this.userRepository.findById(user.getUUID());
        Assert.assertFalse(optionalUser.isPresent());
    }
}
