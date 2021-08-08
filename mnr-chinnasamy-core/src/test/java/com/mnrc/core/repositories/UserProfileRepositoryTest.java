package com.mnrc.core.repositories;

import com.mnrc.core.entities.User;
import com.mnrc.core.entities.UserProfile;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserProfileRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    private UserProfileRepository userProfileRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.userProfileRepository);
    }

    @Test
    public void testSave() throws IOException {
        User user = this.getTestUser();
        this.userRepository.save(user);

        String uuid = UUID.randomUUID().toString();
        Date newDate = new Date();
        InputStream inputStream = this.getClass().getResourceAsStream("/images/user-profile-pic.png");
        int availableBytes = inputStream.available();
        byte[] content = new byte[availableBytes];
        inputStream.read(content);
        inputStream.close();

        UserProfile userProfile = new UserProfile();
        userProfile.setUUID(uuid);
        userProfile.setProfilePicture(content);
        userProfile.setCreatedDate(newDate);
        userProfile.setModifiedDate(newDate);
        userProfile.setUser(user);

        this.userProfileRepository.save(userProfile);

        UserProfile response = this.userProfileRepository.findById(uuid).get();

        Assert.assertNotNull(response);
    }

    @Test
    public void testFindByUserUUID_When_UserProfile_Does_Not_Exists() throws IOException {
        User user = this.getTestUser();
        this.userRepository.save(user);

        UserProfile response = this.userProfileRepository.findByUserUUID(user.getUUID());

        Assert.assertNull(response);
    }

    @Test
    public void testFindByUserUUID_When_UserProfile_Does_Exists() throws IOException {
        User user = this.getTestUser();
        this.userRepository.save(user);

        String uuid = UUID.randomUUID().toString();
        Date newDate = new Date();
        InputStream inputStream = this.getClass().getResourceAsStream("/images/user-profile-pic.png");
        int availableBytes = inputStream.available();
        byte[] content = new byte[availableBytes];
        inputStream.read(content);
        inputStream.close();

        UserProfile userProfile = new UserProfile();
        userProfile.setUUID(uuid);
        userProfile.setProfilePicture(content);
        userProfile.setCreatedDate(newDate);
        userProfile.setModifiedDate(newDate);
        userProfile.setUser(user);
        this.userProfileRepository.save(userProfile);

        UserProfile response = this.userProfileRepository.findByUserUUID(user.getUUID());

        Assert.assertNotNull(response);
    }
}
