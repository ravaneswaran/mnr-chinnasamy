package com.mnrc.forex.commodity.services;

import com.mnrc.forex.commodity.entities.GoldPrice;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoldPriceServiceTest {

    @Autowired
    private GoldPriceService goldPriceService;

    @Test
    public void testNotNull(){
        Assert.assertTrue(null != this.goldPriceService);
    }

    @Test
    public void testSaveGoldPrice(){
        GoldPrice goldPrice = this.getTestGoldPrice();

        this.goldPriceService.save(goldPrice);
        GoldPrice response = this.goldPriceService.getGoldPrice(goldPrice.getUUID());

        Assert.assertEquals(goldPrice.getUUID(), response.getUUID());
    }

    private GoldPrice getTestGoldPrice(){

        GoldPrice goldPrice = new GoldPrice();

        Date now = new Date();
        goldPrice.setUUID(UUID.randomUUID().toString());
        goldPrice.setCreatedDate(now);
        goldPrice.setModifiedDate(now);

        return goldPrice;
    }

}
