package com.mnrc.administration.services.impl;

import com.mnrc.administration.models.PaymentGateway;
import com.mnrc.administration.repositories.PaymentGatewayRepository;
import com.mnrc.administration.services.PaymentGatewayService;
import com.mnrc.administration.ui.forms.PaymentGatewayForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    @Autowired
    private PaymentGatewayRepository paymentGatewayRepository;

    @Override
    public PaymentGatewayForm addPaymentGateway(String name, String merchantId, String paymentGatewayKey, String paymentGatewaySecret, String userFullName) {

        PaymentGateway paymentGateway = new PaymentGateway();
        paymentGateway.setName(name);
        paymentGateway.setMerchantId(merchantId);
        String imagePath = String.format("/images/%s.png", name.toLowerCase().replace(" ", "-"));
        paymentGateway.setPaymentGatewayLogo(imagePath);
        paymentGateway.setPaymentGatewayKey(paymentGatewayKey);
        paymentGateway.setPaymentGatewaySecret(paymentGatewaySecret);
        paymentGateway.setEnabled(0);
        paymentGateway.setCreatedBy(userFullName);
        paymentGateway.setModifiedBy(userFullName);
        Date now = new Date();
        paymentGateway.setCreatedDate(now);
        paymentGateway.setModifiedDate(now);

        PaymentGateway response = this.paymentGatewayRepository.save(paymentGateway);

        PaymentGatewayForm paymentGatewayForm = new PaymentGatewayForm();
        paymentGatewayForm.setPaymentGatewayUUID(response.getUUID());

        return paymentGatewayForm;
    }
}
