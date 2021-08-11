package com.mnrc.core.services;

import com.mnrc.core.forms.PaymentGatewayForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PaymentGatewayService {

    public PaymentGatewayForm addPaymentGateway(String name, String merchantId, String paymentGatewayKey, String paymentGatewaySecret, String callbackUrl, String userFullName) throws Exception;

    public List<PaymentGatewayForm> getPaymentGateways();

    public PaymentGatewayForm getPaymentGateway(String paymentGatewayUUID) throws Exception;

    public PaymentGatewayForm editPaymentGateway(String paymentGatewayUUID, String name, String merchantId, String paymentGatewayKey, String paymentGatewaySecret, String callbackUrl, String userFullName) throws Exception;

    public void deletePaymentGateway(String paymentGatewayUUID) throws Exception;

    public String enablePaymentGateway(String paymentGatewayId) throws Exception;

    public void disablePaymentGateway(String paymentGatewayId) throws Exception;
}
