package com.mnrc.core.services;

public interface OrderService {

    public String createNewOrder(String userId) throws Exception;

    public String changeOrderState(String userId, String orderId, String stateToBe) throws Exception;

    public String confirmOrder(String userId, String orderId) throws Exception;

    public String cancelOrder(String userId, String orderId) throws Exception;

    public String rejectOrder(String userId, String orderId) throws Exception;

}
