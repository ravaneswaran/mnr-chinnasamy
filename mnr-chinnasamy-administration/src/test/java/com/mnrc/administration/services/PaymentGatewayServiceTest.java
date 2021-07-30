package com.mnrc.administration.services;

import com.mnrc.administration.ui.forms.PaymentGatewayForm;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentGatewayServiceTest {

    @Autowired
    private PaymentGatewayService paymentGatewayService;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.paymentGatewayService);
    }

    @Test
    public void testAddPaymentGateway(){
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());

        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        Assert.assertNotNull(paymentGatewayForm.getPaymentGatewayUUID());
    }

}
