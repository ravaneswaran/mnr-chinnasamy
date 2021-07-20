package com.mnrc.administration.repositories;

import com.mnrc.administration.models.Item;
import com.mnrc.administration.models.Order;
import com.mnrc.administration.models.OrderItem;
import com.mnrc.administration.models.User;
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
public class OrderItemRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.orderItemRepository);
    }

    @Test
    public void testSave(){
        User user = this.getTestUser();
        this.userRepository.save(user);

        Item item = this.getTestItem(user);
        this.itemRepository.save(item);

        Order order = this.getTestOrder(user);
        this.orderRepository.save(order);

        String uuid = UUID.randomUUID().toString();
        Date newDate = new Date();

        OrderItem orderItem = new OrderItem();
        orderItem.setUUID(uuid);
        orderItem.setItem(item);
        orderItem.setOrder(order);
        orderItem.setCreatedDate(newDate);
        orderItem.setModifiedDate(newDate);

        this.orderItemRepository.save(orderItem);

        OrderItem result = this.orderItemRepository.findById(uuid).get();

        Assert.assertNotNull(result);
        Assert.assertEquals(uuid, result.getUUID());
    }
}
