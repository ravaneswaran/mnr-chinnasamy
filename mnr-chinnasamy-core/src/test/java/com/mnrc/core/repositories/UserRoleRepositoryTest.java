package com.mnrc.core.repositories;

import com.mnrc.core.entities.UserRole;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRoleRepositoryTest {

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.userRoleRepository);
    }

    @Test
    public void testSave(){
        String uuid = UUID.randomUUID().toString();
        Date now = new Date();

        UserRole userRole = new UserRole();
        userRole.setUUID(uuid);
        userRole.setName(RandomString.make().toUpperCase());
        userRole.setCreatedBy("ALMIGHTY");
        userRole.setModifiedBy("ALMIGHTY");
        userRole.setCreatedDate(now);
        userRole.setModifiedDate(now);

        UserRole response = this.userRoleRepository.save(userRole);

        Assert.assertEquals(uuid, response.getUUID());
    }

    @Test
    public void testFindAllExcludingAlmightyRole(){
        String uuid = UUID.randomUUID().toString();
        Date now = new Date();

        UserRole userRole = new UserRole();
        userRole.setUUID(uuid);
        userRole.setName(RandomString.make());
        userRole.setCreatedBy("ALMIGHTY");
        userRole.setModifiedBy("ALMIGHTY");
        userRole.setCreatedDate(now);
        userRole.setModifiedDate(now);
        this.userRoleRepository.save(userRole);

        List<UserRole> userRoles = this.userRoleRepository.findAllExcludingAlmightyRole();

        for (UserRole usrRole: userRoles) {
            Assert.assertTrue(!"ALMIGHTY".equals(usrRole.getName()));
        }
    }
}
