package com.mnrc.administration.repositories;

import com.mnrc.administration.models.Address;
import com.mnrc.administration.models.User;
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
public class AddressRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.addressRepository);
    }

    @Test
    public void testSave(){
        User user = this.getTestUser();
        this.userRepository.save(user);

        String uuid = UUID.randomUUID().toString();
        String zipCode = String.valueOf(new Random().nextLong());
        Date newDate = new Date();

        Address address = new Address();
        address.setUUID(uuid);
        address.setAddressLine1("address line one");
        address.setAddressLine2("address line two");
        address.setAddressLine3("address line three");
        address.setCity("test city");
        address.setState("test state");
        address.setCountry("test country");
        address.setZipCode(zipCode);
        address.setType("PERMANENT-ADDRESS");
        address.setCreatedDate(newDate);
        address.setModifiedDate(newDate);
        address.setUser(user);

        this.addressRepository.save(address);

        Address result = this.addressRepository.findById(uuid).get();

        Assert.assertNotNull(result);
        Assert.assertEquals(uuid, result.getUUID());

    }
}
