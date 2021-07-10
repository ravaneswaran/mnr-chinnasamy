package com.mnrc.repositories;

import com.mnrc.models.Item;
import com.mnrc.models.ItemRangePrice;
import com.mnrc.models.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemRangePriceRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    private ItemRangePriceRepository itemRangePriceRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.itemRangePriceRepository);
    }

    @Test
    public void testSave(){
        User user = this.getTestUser();
        this.userRepository.save(user);

        Item item = this.getTestItem(user);
        this.itemRepository.save(item);

        ItemRangePrice itemRangePrice = new ItemRangePrice();
        itemRangePrice.setLow1(1);
        itemRangePrice.setHigh1(99);
        itemRangePrice.setPrice1("100");
        itemRangePrice.setLow2(100);
        itemRangePrice.setHigh2(199);
        itemRangePrice.setPrice2("200");
        itemRangePrice.setLow3(200);
        itemRangePrice.setHigh3(299);
        itemRangePrice.setPrice3("300");
        itemRangePrice.setLow4(300);
        itemRangePrice.setHigh4(399);
        itemRangePrice.setPrice1("400");
        itemRangePrice.setCreatedBy(user.getUUID());
        Date date = new Date();
        itemRangePrice.setCreatedDate(date);
        itemRangePrice.setModifiedDate(date);

        this.itemRangePriceRepository.save(itemRangePrice);

        ItemRangePrice result = this.itemRangePriceRepository.findById(itemRangePrice.getUUID()).get();

        Assert.assertNotNull(result);
        Assert.assertEquals(itemRangePrice.getUUID(), result.getUUID());

    }

}
