package com.webshoppe.repositories;

import com.webshoppe.enums.OrderState;
import com.webshoppe.models.Order;
import com.webshoppe.models.User;
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
public class OrderRepositoryTest extends AbstractRepositoryTest{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.orderRepository);
    }

    @Test
    public void testSave(){
        User user = this.getTestUser();
        this.userRepository.save(user);

        String uuid = UUID.randomUUID().toString();
        String state = OrderState.INITIATED.toString();
        Date newDate = new Date();

        Order order = new Order();

        order.setUUID(uuid);
        order.setState(state);
        order.setCreatedDate(newDate);
        order.setModifiedDate(newDate);
        order.setUser(user);

        this.orderRepository.save(order);

        Order result = this.orderRepository.findById(uuid).get();

        Assert.assertNotNull(result);
        Assert.assertEquals(uuid, result.getUUID());

    }

}
