package com.shoppe.services;

import com.shoppe.enums.UserStatus;
import com.shoppe.enums.UserType;
import com.shoppe.models.Token;
import com.shoppe.models.User;
import com.shoppe.repositories.TokenRepository;
import com.shoppe.repositories.UserRepository;
import com.shoppe.services.vo.UserVO;
import com.shoppe.ui.forms.AdminForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
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
    public void testAddAdmin() {
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String status = UserStatus.VERIFIED.toString();
        String type = UserType.ADMIN.toString();

        AdminForm adminForm = this.userService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, type, status);

        Assert.assertNotNull(adminForm.getAdminId());
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

    @Test
    public void testListAdmins(){
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String status = UserStatus.VERIFIED.toString();
        String type = UserType.ADMIN.toString();

        this.userService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, type, status);

        List<AdminForm> adminForms = this.userService.listAdmins();

        Assert.assertTrue(adminForms.size() > 0);

    }

    @Test
    public void testBlockUser(){

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String status = UserStatus.VERIFIED.toString();
        String type = UserType.ADMIN.toString();
        AdminForm adminForm = this.userService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, type, status);

        this.userService.blockUser(adminForm.getAdminId());

        User user = this.userRepository.findById(adminForm.getAdminId()).get();
        Assert.assertEquals(UserStatus.BLOCKED.toString(), user.getStatus());
    }

    @Test
    public void testUnBlockUser(){

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String status = UserStatus.VERIFIED.toString();
        String type = UserType.ADMIN.toString();
        AdminForm adminForm = this.userService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, type, status);
        this.userService.blockUser(adminForm.getAdminId());

        this.userService.unblockUser(adminForm.getAdminId());

        User user = this.userRepository.findById(adminForm.getAdminId()).get();
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
        String emailId = String.format("mail-%s", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        String status = UserStatus.VERIFIED.toString();
        String type = UserType.ADMIN.toString();
        AdminForm adminForm = this.userService.addAdmin(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo, type, status);

        this.userService.deleteUser(adminForm.getAdminId());

        Optional<User> optionalUser = this.userRepository.findById(adminForm.getAdminId());
        Assert.assertFalse(optionalUser.isPresent());
    }
}
