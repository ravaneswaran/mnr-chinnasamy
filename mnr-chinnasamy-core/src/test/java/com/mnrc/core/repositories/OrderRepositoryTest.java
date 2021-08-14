package com.mnrc.core.repositories;

import com.mnrc.core.AbstractCoreTest;
import com.mnrc.core.entities.Order;
import com.mnrc.core.entities.User;
import com.mnrc.core.entities.UserRole;
import com.mnrc.core.enums.OrderState;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderRepositoryTest extends AbstractCoreTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Test
    public void testNotNull(){
        Assert.assertTrue(null != this.orderRepository);
    }

    @Test
    public void testSave(){
        UserRole userRole = this.getTestRole();
        this.userRoleRepository.save(userRole);

        User user = this.getTestUser();
        user.setRole(userRole);
        this.userRepository.save(user);

        Date now = new Date();
        String userFullName = String.format("%s %s %s", user.getFirstName(), user.getMiddleInitial(), user.getLastName()).trim();

        Order order = new Order();
        order.setUser(user);
        order.setState(OrderState.PLACED.toString());
        order.setCreatedDate(now);
        order.setModifiedDate(now);
        order.setCreatedBy(userFullName);
        order.setModifiedBy(userFullName);
        this.orderRepository.save(order);

        Order response = this.orderRepository.findById(order.getUUID()).get();

        Assert.assertEquals(order.getUUID(), response.getUUID());
    }

}
