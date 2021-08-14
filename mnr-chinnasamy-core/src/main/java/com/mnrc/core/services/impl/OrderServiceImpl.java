package com.mnrc.core.services.impl;

import com.mnrc.core.entities.Order;
import com.mnrc.core.entities.User;
import com.mnrc.core.enums.OrderState;
import com.mnrc.core.repositories.OrderRepository;
import com.mnrc.core.repositories.UserRepository;
import com.mnrc.core.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public String createNewOrder(String userId) throws Exception {
        if(null == userId || "".equals(userId)){
            throw new Exception("user id cannot be null or empty...");
        }

        Optional<User> optionalUser = this.userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new Exception("No such user present...");
        }

        User user = optionalUser.get();
        Date now = new Date();
        String userName = String.format("%s %s %s", user.getFirstName(), user.getMiddleInitial(), user.getLastName()).trim();

        Order order = new Order();
        order.setUser(user);
        order.setState(OrderState.PLACED.toString());
        order.setCreatedDate(now);
        order.setModifiedDate(now);
        order.setCreatedBy(userName);
        order.setModifiedBy(userName);

        this.orderRepository.save(order);

        return order.getUUID();
    }

    @Override
    public String changeOrderState(String userId, String orderId, String stateToBe) throws Exception {

        if(null == userId || "".equals(userId)){
            throw new Exception("user id cannot be null or empty...");
        }

        Optional<User> optionalUser = this.userRepository.findById(userId);
        if(!optionalUser.isPresent()){
            throw new Exception("No such user present...");
        }

        if(null == orderId || "".equals(orderId)){
            throw new Exception("order id cannot be null or empty...");
        }

        Optional<Order> optionalOrder = this.orderRepository.findById(orderId);
        if(!optionalOrder.isPresent()){
            throw new Exception("No such order present...");
        }

        User user = optionalUser.get();
        Date now = new Date();
        String userName = String.format("%s %s %s", user.getFirstName(), user.getMiddleInitial(), user.getLastName()).trim();

        Order order = optionalOrder.get();
        order.setState(stateToBe);
        order.setModifiedBy(userName);
        order.setModifiedDate(now);

        this.orderRepository.save(order);

        return order.getUUID();
    }

    @Override
    public String confirmOrder(String userId, String orderId) throws Exception {
        return this.changeOrderState(userId, orderId, OrderState.CONFIRMED.toString());
    }

    @Override
    public String cancelOrder(String userId, String orderId) throws Exception {
        return this.changeOrderState(userId, orderId, OrderState.CANCELLED.toString());
    }

    @Override
    public String rejectOrder(String userId, String orderId) throws Exception {
        return this.changeOrderState(userId, orderId, OrderState.REJECTED.toString());
    }
}
