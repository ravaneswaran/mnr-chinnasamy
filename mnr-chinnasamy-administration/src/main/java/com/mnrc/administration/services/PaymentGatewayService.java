package com.mnrc.administration.services;

import com.mnrc.administration.ui.forms.PaymentGatewayForm;

import java.util.List;

public interface PaymentGatewayService {

    public PaymentGatewayForm addPaymentGateway(String name, String merchantId, String paymentGatewayKey, String paymentGatewaySecret, String callbackUrl, String userFullName);

    public List<PaymentGatewayForm> getPaymentGateways();

}
