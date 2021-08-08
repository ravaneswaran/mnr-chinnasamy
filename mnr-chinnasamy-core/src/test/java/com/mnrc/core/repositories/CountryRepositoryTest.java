package com.mnrc.core.repositories;

import com.mnrc.core.entities.Country;
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
public class CountryRepositoryTest {

    @Autowired
    private CountryRepository countryRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.countryRepository);
    }

    @Test
    public void testSave(){
        String uuid = UUID.randomUUID().toString();
        Date newDate = new Date();
        long suffix = new Random().nextLong();
        String name = String.format("name-%s", suffix);
        String code = String.valueOf(new Random().nextInt());

        Country country = new Country();

        country.setUUID(uuid);
        country.setName(name);
        country.setCode(code);
        country.setCreatedDate(newDate);
        country.setModifiedDate(newDate);

        this.countryRepository.save(country);

        Country result = this.countryRepository.findById(uuid).get();

        Assert.assertNotNull(this.countryRepository);
        Assert.assertEquals(uuid, result.getUUID());
    }

}
