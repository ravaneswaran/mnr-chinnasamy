package com.mnrc.core.repositories;

import com.mnrc.core.entities.PaymentGateway;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentGatewayRepositoryTest {

    @Autowired
    private PaymentGatewayRepository paymentGatewayRepository;

    @Test
    public void testNotNull(){
        Assert.assertNotNull(this.paymentGatewayRepository);
    }

    @Test
    public void testSave(){

        PaymentGateway paymentGateway = new PaymentGateway();

        paymentGateway.setName("Razor Pay");
        paymentGateway.setMerchantId(RandomString.make());
        paymentGateway.setPaymentGatewayLogo("/images/razorpay-pg.png");
        paymentGateway.setPaymentGatewayKey(RandomString.make());
        paymentGateway.setPaymentGatewaySecret(RandomString.make());
        paymentGateway.setCallbackUrl("/razor-pay/callback");
        paymentGateway.setEnabled(0);
        String user = "Test";
        paymentGateway.setCreatedBy(user);
        paymentGateway.setModifiedBy(user);
        Date now = new Date();
        paymentGateway.setCreatedDate(now);
        paymentGateway.setModifiedDate(now);

        PaymentGateway response = this.paymentGatewayRepository.save(paymentGateway);
        Assert.assertTrue(null != response);

    }
}
