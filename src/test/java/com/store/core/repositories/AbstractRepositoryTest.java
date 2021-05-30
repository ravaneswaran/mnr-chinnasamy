package com.store.core.repositories;

import com.store.core.enums.OrderState;
import com.store.core.models.Item;
import com.store.core.models.Order;
import com.store.core.models.User;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class AbstractRepositoryTest {

    protected User getTestUser(){
        String uuid = UUID.randomUUID().toString();

        Random random = new Random();
        String randomNumberString = String.valueOf(Math.abs(random.nextLong()));
        Date newDate = new Date();

        User user = new User();
        user.setUUID(uuid);
        user.setFirstName("Test");
        user.setMiddleInitial("");
        user.setLastName("Test");
        user.setEmailId(String.format("mail%s@test.com", randomNumberString));
        user.setUniqueId(randomNumberString);
        user.setMobileNo(randomNumberString);
        user.setPassword(String.format("password%s", randomNumberString));
        user.setStatus("YET-TO-VERIFY");
        user.setCreatedDate(newDate);
        user.setModifiedDate(newDate);

        return user;
    }

    protected Order getTestOrder(User user){

        String uuid = UUID.randomUUID().toString();
        String state = OrderState.INITIATED.toString();
        Date newDate = new Date();

        Order order = new Order();

        order.setUUID(uuid);
        order.setState(state);
        order.setCreatedDate(newDate);
        order.setModifiedDate(newDate);
        order.setUser(user);

        return order;
    }

    protected Item getTestItem(User user){
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

        return item;
    }
}
