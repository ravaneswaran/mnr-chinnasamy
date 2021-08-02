package com.mnrc.administration.services;

import com.mnrc.administration.ui.forms.PaymentGatewayForm;
import net.bytebuddy.utility.RandomString;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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
    public void testAddPaymentGateway_When_Name_Is_Null() {
        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(null, null, null, null, null, null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("Payment gateway name cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_MerchantId_Is_Null() {
        String name = RandomString.make();
        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, null, null, null, null, null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("Merchant id cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_Payment_Gateway_Key_Is_Null() {
        String name = RandomString.make();
        String merchantId = RandomString.make();

        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, null, null, null, null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("Payment gateway key cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_Payment_Gateway_Secret_Is_Null() {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();

        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, null, null, null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("Payment gateway secret cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_callback_URL_Is_Null() {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();

        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, null, null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("Call back url cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_User_Full_Name_Is_Null() {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());

        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("User full name cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_Name_Is_Empty() {
        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway("", null, null, null, null, null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("Payment gateway name cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_MerchantId_Is_Empty() {
        String name = RandomString.make();
        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, "", null, null, null, null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("Merchant id cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_Payment_Gateway_Key_Is_Empty() {
        String name = RandomString.make();
        String merchantId = RandomString.make();

        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, "", null, null, null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("Payment gateway key cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_Payment_Gateway_Secret_Is_Empty() {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();

        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, "", null, null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("Payment gateway secret cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_callback_URL_Is_Empty() {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();

        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, "", null);
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("Call back url cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway_When_User_Full_Name_Is_Empty() {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());

        PaymentGatewayForm paymentGatewayForm = null;
        try {
            paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, "");
        }catch(Exception exp){
            String message = exp.getMessage();
            Assert.assertEquals("User full name cannot be null or empty...", message);
        }
    }

    @Test
    public void testAddPaymentGateway() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());

        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        Assert.assertNotNull(paymentGatewayForm.getPaymentGatewayUUID());
    }

    /*-------------------------------------------------*/

    @Test
    public void testEditPaymentGateway_When_Payment_Gateway_UUID_Is_Null() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        try{
            this.paymentGatewayService.editPaymentGateway(null, name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);
        } catch (Exception exp){
            Assert.assertEquals("Payment Gateway id cannot be null or empty...", exp.getMessage());
        }
    }

    @Test
    public void testEditPaymentGateway_When_Name_Is_Null() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        try{
            this.paymentGatewayService.editPaymentGateway(paymentGatewayForm.getPaymentGatewayUUID(), null, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);
        } catch (Exception exp){
            Assert.assertEquals("Payment gateway name cannot be null or empty...", exp.getMessage());
        }
    }

    @Test
    public void testEditPaymentGateway_When_MerchantId_Is_Null() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        try{
            this.paymentGatewayService.editPaymentGateway(paymentGatewayForm.getPaymentGatewayUUID(), name, null, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);
        } catch (Exception exp){
            Assert.assertEquals("Merchant id cannot be null or empty...", exp.getMessage());
        }
    }

    @Test
    public void testEditPaymentGateway_When_Payment_Gateway_Key_Is_Null() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        try{
            this.paymentGatewayService.editPaymentGateway(paymentGatewayForm.getPaymentGatewayUUID(), name, merchantId, null, paymentGatewaySecret, callbackUrl, userFullName);
        } catch (Exception exp){
            Assert.assertEquals("Payment gateway key cannot be null or empty...", exp.getMessage());
        }
    }

    @Test
    public void testEditPaymentGateway_When_Payment_Gateway_Secret_Is_Null() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        try{
            this.paymentGatewayService.editPaymentGateway(paymentGatewayForm.getPaymentGatewayUUID(), name, merchantId, paymentGatewayKey, null, callbackUrl, userFullName);
        } catch (Exception exp){
            Assert.assertEquals("Payment gateway secret cannot be null or empty...", exp.getMessage());
        }
    }

    @Test
    public void testEditPaymentGateway_When_callback_URL_Is_Null() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        try{
            this.paymentGatewayService.editPaymentGateway(paymentGatewayForm.getPaymentGatewayUUID(), name, merchantId, paymentGatewayKey, paymentGatewaySecret, null, userFullName);
        } catch (Exception exp){
            Assert.assertEquals("Call back url cannot be null or empty...", exp.getMessage());
        }
    }

    @Test
    public void testEditPaymentGateway_When_User_Full_Name_Is_Null() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        try{
            this.paymentGatewayService.editPaymentGateway(paymentGatewayForm.getPaymentGatewayUUID(), name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, null);
        } catch (Exception exp){
            Assert.assertEquals("User full name cannot be null or empty...", exp.getMessage());
        }
    }

    @Test
    public void testEditPaymentGateway() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        String editName = RandomString.make();
        PaymentGatewayForm response = this.paymentGatewayService.editPaymentGateway(paymentGatewayForm.getPaymentGatewayUUID(), editName, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        Assert.assertNotNull(paymentGatewayForm.getPaymentGatewayUUID().equals(response.getPaymentGatewayUUID()));
        Assert.assertNotEquals(name, response.getName());
    }

    @Test
    public void testGetPaymentGateways() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        List<PaymentGatewayForm> paymentGatewayForms = this.paymentGatewayService.getPaymentGateways();

        Assert.assertTrue(paymentGatewayForms.size() > 0);
    }

    @Test
    public void testGetPaymentGateway() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);

        PaymentGatewayForm response = this.paymentGatewayService.getPaymentGateway(paymentGatewayForm.getPaymentGatewayUUID());

        Assert.assertTrue(paymentGatewayForm.getPaymentGatewayUUID().equals(response.getPaymentGatewayUUID()));
    }

    @Test
    public void testDeletePaymentGateway() throws Exception {
        String name = RandomString.make();
        String merchantId = RandomString.make();
        String paymentGatewayKey = RandomString.make();
        String paymentGatewaySecret = RandomString.make();
        String callbackUrl = String.format("/%s/%s", RandomString.make(), RandomString.make());
        String userFullName = String.format("%s %s", RandomString.make(), RandomString.make());
        PaymentGatewayForm paymentGatewayForm = this.paymentGatewayService.addPaymentGateway(name, merchantId, paymentGatewayKey, paymentGatewaySecret, callbackUrl, userFullName);
        this.paymentGatewayService.deletePaymentGateway(paymentGatewayForm.getPaymentGatewayUUID());

        try{
            this.paymentGatewayService.getPaymentGateway(paymentGatewayForm.getPaymentGatewayUUID());
        } catch(Exception exp){
            Assert.assertTrue("Invalid Payment Gateway id...".equals(exp.getMessage()));
        }
    }
}
