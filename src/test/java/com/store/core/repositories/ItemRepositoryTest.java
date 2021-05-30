package com.store.core.repositories;

import com.store.core.models.Item;
import com.store.core.models.User;
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
public class ItemRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.itemRepository);
    }

    @Test
    public void testSave(){

        User user = this.getTestUser();
        this.userRepository.save(user);

        String uuid = UUID.randomUUID().toString();
        Date newDate = new Date();
        String randomLongString = String.valueOf(Math.abs(new Random().nextLong()));

        Item item = new Item();

        item.setUUID(uuid);
        item.setName(String.format("name-%s", randomLongString));
        item.setCode(String.format("code-%s", randomLongString));
        item.setUnitPrice("100.0");
        item.setCreatedDate(newDate);
        item.setModifiedDate(newDate);
        item.setUser(user);

        this.itemRepository.save(item);

        Item result = this.itemRepository.findById(uuid).get();

        Assert.assertNotNull(result);
        Assert.assertEquals(uuid, result.getUUID());
    }

}
