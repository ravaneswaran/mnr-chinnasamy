package com.mnrc.core.services;

import com.mnrc.core.entities.User;
import com.mnrc.core.entities.UserRole;
import com.mnrc.core.enums.UserStatus;
import com.mnrc.core.enums.UserType;
import com.mnrc.core.forms.LoginForm;
import com.mnrc.core.repositories.UserRepository;
import com.mnrc.core.repositories.UserRoleRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginServiceTest {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.loginService);
    }

    @Test
    public void testLogin() throws Exception {

        String roleName = RandomString.make();
        String userFullName = "Almighty";
        Date now = new Date();
        String userRoleUUID = UUID.randomUUID().toString();

        UserRole userRole = new UserRole();
        userRole.setUUID(userRoleUUID);
        userRole.setName(roleName);
        userRole.setCreatedBy(userFullName);
        userRole.setModifiedBy(userFullName);
        userRole.setCreatedDate(now);
        userRole.setModifiedDate(now);
        this.userRoleRepository.save(userRole);

        String userUUID = UUID.randomUUID().toString();
        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        Date newDate = new Date();

        String emailId = String.format("mail%s@test.com", randomNumberString);
        String password = String.format("password%s", randomNumberString);

        User user = new User();
        user.setUUID(userUUID);
        user.setFirstName("Ravaneswaran");
        user.setMiddleInitial("");
        user.setLastName("Chinnasamy");
        user.setEmailId(emailId);
        user.setUniqueId(randomNumberString);
        user.setMobileNo(randomNumberString);
        user.setPassword(String.format("password%s", randomNumberString));
        user.setType(UserType.ADMIN.toString());
        user.setStatus(UserStatus.VERIFIED.toString());
        user.setRole(userRole);
        user.setCreatedDate(newDate);
        user.setModifiedDate(newDate);
        this.userRepository.save(user);

        LoginForm login = this.loginService.login(emailId, password);

        Assert.assertEquals(user.getUUID(), login.getUserId());

    }
}
