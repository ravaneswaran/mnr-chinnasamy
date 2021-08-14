package com.mnrc.core.services;

import com.mnrc.core.entities.User;
import com.mnrc.core.entities.UserProfile;
import com.mnrc.core.enums.UserStatus;
import com.mnrc.core.enums.UserType;
import com.mnrc.core.repositories.UserRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileServiceTest {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.userProfileService);
    }

    @Test
    public void testAddProfilePicture() throws IOException {
        String uuid = UUID.randomUUID().toString();

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        Date newDate = new Date();
        String emailId = String.format("mail%s@test.com", randomNumberString);
        String password = String.format("password%s", randomNumberString);

        User user = new User();
        user.setUUID(uuid);
        user.setFirstName("Ravaneswaran");
        user.setMiddleInitial("");
        user.setLastName("Chinnasamy");
        user.setEmailId(emailId);
        user.setUniqueId(randomNumberString);
        user.setMobileNo(randomNumberString);
        user.setPassword(password);
        user.setType(UserType.ADMIN.toString());
        user.setStatus(UserStatus.VERIFIED.toString());
        user.setCreatedDate(newDate);
        user.setModifiedDate(newDate);
        this.userRepository.save(user);

        InputStream inputStream = this.getClass().getResourceAsStream("/images/user-profile-pic.png");
        int availableBytes = inputStream.available();
        byte[] content = new byte[availableBytes];
        inputStream.read(content);
        inputStream.close();

        UserProfile userProfile = this.userProfileService.saveProfilePicture(uuid, content);

        Assert.assertNotNull(userProfile);
    }
}
