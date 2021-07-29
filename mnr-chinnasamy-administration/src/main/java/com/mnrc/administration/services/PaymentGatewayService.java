package com.mnrc.administration.services;

import com.mnrc.administration.ui.forms.PaymentGatewayForm;

public interface PaymentGatewayService {

    public PaymentGatewayForm addPaymentGateway(String name, String merchantId, String paymentGatewayKey, String paymentGatewaySecret, String userFullName);

}
