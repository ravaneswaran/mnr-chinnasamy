package com.mnrc.core.services;

import com.mnrc.core.AbstractCoreTest;
import com.mnrc.core.entities.Order;
import com.mnrc.core.entities.User;
import com.mnrc.core.entities.UserRole;
import com.mnrc.core.enums.OrderState;
import com.mnrc.core.repositories.OrderRepository;
import com.mnrc.core.repositories.UserRepository;
import com.mnrc.core.repositories.UserRoleRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceTest extends AbstractCoreTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateNewOrder() throws Exception {
        UserRole userRole = this.getTestRole();
        this.userRoleRepository.save(userRole);

        User user = this.getTestUser();
        user.setRole(userRole);
        this.userRepository.save(user);

        String orderId = this.orderService.createNewOrder(user.getUUID());

        Order order = this.orderRepository.findById(orderId).get();

        Assert.assertEquals(orderId, order.getUUID());
    }

    @Test
    public void testChangeOrderState() throws Exception {
        UserRole userRole = this.getTestRole();
        this.userRoleRepository.save(userRole);

        User user = this.getTestUser();
        user.setRole(userRole);
        this.userRepository.save(user);

        String orderId = this.orderService.createNewOrder(user.getUUID());
        Order order = this.orderRepository.findById(orderId).get();

        Assert.assertEquals("PLACED", OrderState.PLACED.toString());

        String responseOrderId = this.orderService.changeOrderState(user.getUUID(), order.getUUID(), OrderState.CONFIRMED.toString());
        Order response = this.orderRepository.findById(responseOrderId).get();

        Assert.assertNotEquals("PLACED", response.getState());
        Assert.assertEquals(OrderState.CONFIRMED.toString(), response.getState());
    }

    @Test
    public void testConfirmOrder() throws Exception {
        UserRole userRole = this.getTestRole();
        this.userRoleRepository.save(userRole);

        User user = this.getTestUser();
        user.setRole(userRole);
        this.userRepository.save(user);

        String orderId = this.orderService.createNewOrder(user.getUUID());
        Order order = this.orderRepository.findById(orderId).get();

        Assert.assertEquals("PLACED", OrderState.PLACED.toString());

        String responseOrderId = this.orderService.confirmOrder(user.getUUID(), order.getUUID());
        Order response = this.orderRepository.findById(responseOrderId).get();

        Assert.assertNotEquals("PLACED", response.getState());
        Assert.assertEquals(OrderState.CONFIRMED.toString(), response.getState());
    }

    @Test
    public void testCancelOrder() throws Exception {
        UserRole userRole = this.getTestRole();
        this.userRoleRepository.save(userRole);

        User user = this.getTestUser();
        user.setRole(userRole);
        this.userRepository.save(user);

        String orderId = this.orderService.createNewOrder(user.getUUID());
        Order order = this.orderRepository.findById(orderId).get();

        Assert.assertEquals("PLACED", OrderState.PLACED.toString());

        String responseOrderId = this.orderService.cancelOrder(user.getUUID(), order.getUUID());
        Order response = this.orderRepository.findById(responseOrderId).get();

        Assert.assertNotEquals("PLACED", response.getState());
        Assert.assertEquals(OrderState.CANCELLED.toString(), response.getState());
    }

    @Test
    public void testRejectOrder() throws Exception {
        UserRole userRole = this.getTestRole();
        this.userRoleRepository.save(userRole);

        User user = this.getTestUser();
        user.setRole(userRole);
        this.userRepository.save(user);

        String orderId = this.orderService.createNewOrder(user.getUUID());
        Order order = this.orderRepository.findById(orderId).get();

        Assert.assertEquals("PLACED", OrderState.PLACED.toString());

        String responseOrderId = this.orderService.rejectOrder(user.getUUID(), order.getUUID());
        Order response = this.orderRepository.findById(responseOrderId).get();

        Assert.assertNotEquals("PLACED", response.getState());
        Assert.assertEquals(OrderState.REJECTED.toString(), response.getState());
    }
}
