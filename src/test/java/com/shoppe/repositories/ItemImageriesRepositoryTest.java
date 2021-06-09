package com.shoppe.repositories;

import com.shoppe.models.Item;
import com.shoppe.models.ItemImage;
import com.shoppe.models.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemImageriesRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ItemImageriesRepository itemImageriesRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.itemImageriesRepository);
    }

    @Test
    public void testSave(){
        User user = this.getTestUser();
        this.userRepository.save(user);

        Item item = this.getTestItem(user);
        this.itemRepository.save(item);

        ItemImage itemImage = new ItemImage();

        itemImage.setPosition(1);
        itemImage.setItem(item);
        itemImage.setCreatedBy(user.getUUID());
        Date date = new Date();
        itemImage.setCreatedDate(date);
        itemImage.setModifiedDate(date);
        this.itemImageriesRepository.save(itemImage);

        ItemImage result = this.itemImageriesRepository.findById(itemImage.getUUID()).get();

        Assert.assertNotNull(result);
        Assert.assertEquals(itemImage.getUUID(), result.getUUID());
    }
}