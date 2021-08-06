package com.mnrc.core.services;

import com.mnrc.core.enums.UserStatus;
import com.mnrc.core.enums.UserType;
import com.mnrc.core.entities.User;
import com.mnrc.core.repositories.TokenRepository;
import com.mnrc.core.repositories.UserRepository;
import com.mnrc.core.forms.UserForm;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
    public void testAddUserWithVerifiedStatus() throws Exception {
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
    public void testBlockUser() throws Exception {

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

        this.userService.lockUser(user.getUUID());

        User response = this.userRepository.findById(user.getUUID()).get();
        Assert.assertEquals(UserStatus.LOCKED.toString(), response.getStatus());
    }

    @Test
    public void testUnBlockUser() throws Exception {

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
        this.userService.lockUser(user.getUUID());

        this.userService.unLockUser(user.getUUID());

        User response = this.userRepository.findById(user.getUUID()).get();
        Assert.assertEquals(UserStatus.VERIFIED.toString(), user.getStatus());
    }

    @Test
    public void testDeleteUser() throws Exception {

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

    @Test
    public void testAddUser() throws Exception {
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;

        UserForm admin = this.userService.addUser(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        Assert.assertNotNull(admin);
    }

    @Test
    public void testListUsers() throws Exception {
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        this.userService.addUser(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        List<UserForm> admins = this.userService.listUsers();

        Assert.assertTrue(admins.size() >= 1 );
    }

    @Test
    public void testGetUser() throws Exception {
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        String mobileNoString = String.valueOf(Math.abs(random.nextLong()));

        String firstName = "Ravaneswaran";
        String middleInitial = " ";
        String lastName = "Chinnasamy";
        String emailId = String.format("mail-%s@test.com", randomNumberString);
        String uniqueId = randomNumberString;
        String mobileNo = mobileNoString;
        UserForm userForm = this.userService.addUser(firstName, middleInitial, lastName, emailId, uniqueId, mobileNo);

        UserForm response = this.userService.getUserForm(userForm.getUserId());

        Assert.assertEquals(userForm.getUserId(), response.getUserId());
    }
}
