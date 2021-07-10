package com.mnrc.repositories;

import com.mnrc.enums.UserStatus;
import com.mnrc.enums.UserType;
import com.mnrc.models.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.userRepository);
    }

    @Test
    public void testSave(){
        String uuid = UUID.randomUUID().toString();

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        Date newDate = new Date();

        User user = new User();
        user.setUUID(uuid);
        user.setFirstName("Ravaneswaran");
        user.setMiddleInitial("");
        user.setLastName("Chinnasamy");
        user.setEmailId(String.format("mail%s@test.com", randomNumberString));
        user.setUniqueId(randomNumberString);
        user.setMobileNo(randomNumberString);
        user.setPassword(String.format("password%s", randomNumberString));
        user.setType(UserType.ADMIN.toString());
        user.setStatus(UserStatus.VERIFIED.toString());
        user.setCreatedDate(newDate);
        user.setModifiedDate(newDate);
        this.userRepository.save(user);

        User result = this.userRepository.findById(uuid).get();

        Assert.assertNotNull(result);
        Assert.assertEquals(uuid, result.getUUID());
    }

    @Test
    public void testFindByEmailIdAndPassword(){

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
        user.setPassword(String.format("password%s", randomNumberString));
        user.setType(UserType.ADMIN.toString());
        user.setStatus(UserStatus.VERIFIED.toString());
        user.setCreatedDate(newDate);
        user.setModifiedDate(newDate);
        this.userRepository.save(user);

        User response = this.userRepository.findByEmailIdAndPassword(emailId, password);

        Assert.assertEquals(uuid, response.getUUID());
    }

    @Test
    public void testFindByEmailId(){
        String uuid = UUID.randomUUID().toString();

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        Date newDate = new Date();

        String emailId = String.format("mail%s@test.com", randomNumberString);

        User user = new User();
        user.setUUID(uuid);
        user.setFirstName("Ravaneswaran");
        user.setMiddleInitial("");
        user.setLastName("Chinnasamy");
        user.setEmailId(emailId);
        user.setUniqueId(randomNumberString);
        user.setMobileNo(randomNumberString);
        user.setPassword(String.format("password%s", randomNumberString));
        user.setType(UserType.ADMIN.toString());
        user.setStatus(UserStatus.VERIFIED.toString());
        user.setCreatedDate(newDate);
        user.setModifiedDate(newDate);
        this.userRepository.save(user);

        User response = this.userRepository.findByEmailId(emailId);

        Assert.assertEquals(uuid, response.getUUID());
    }

}
