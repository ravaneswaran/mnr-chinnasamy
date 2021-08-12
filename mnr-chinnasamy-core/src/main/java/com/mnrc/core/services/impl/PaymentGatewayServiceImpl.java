package com.mnrc.core.services.impl;

import com.mnrc.core.entities.PaymentGateway;
import com.mnrc.core.forms.PaymentGatewayForm;
import com.mnrc.core.repositories.PaymentGatewayRepository;
import com.mnrc.core.services.PaymentGatewayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentGatewayServiceImpl implements PaymentGatewayService {

    Logger logger = LoggerFactory.getLogger(PaymentGatewayServiceImpl.class);

    @Autowired
    private PaymentGatewayRepository paymentGatewayRepository;

    @Override
    public PaymentGatewayForm addPaymentGateway(String name, String merchantId, String paymentGatewayKey, String paymentGatewaySecret, String callbackUrl, String userFullName) throws Exception {

        if(null == name || "".equals(name)){
            throw new Exception("Payment gateway name cannot be null or empty...");
        }

        if(null == merchantId || "".equals(merchantId)){
            throw new Exception("Merchant id cannot be null or empty...");
        }

        if(null == paymentGatewayKey || "".equals(paymentGatewayKey)){
            throw new Exception("Payment gateway key cannot be null or empty...");
        }

        if(null == paymentGatewaySecret || "".equals(paymentGatewaySecret)){
            throw new Exception("Payment gateway secret cannot be null or empty...");
        }

        if(null == callbackUrl || "".equals(callbackUrl)){
            throw new Exception("Call back url cannot be null or empty...");
        }

        if(null == userFullName || "".equals(userFullName)){
            throw new Exception("User full name cannot be null or empty...");
        }

        PaymentGateway paymentGateway = new PaymentGateway();
        paymentGateway.setName(name);
        paymentGateway.setMerchantId(merchantId);
        String imagePath = String.format("/images/%s.png", name.toLowerCase().replace(" ", "-"));
        paymentGateway.setPaymentGatewayLogo(imagePath);
        paymentGateway.setPaymentGatewayKey(paymentGatewayKey);
        paymentGateway.setPaymentGatewaySecret(paymentGatewaySecret);
        paymentGateway.setCallbackUrl(callbackUrl);
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

    @Override
    public List<PaymentGatewayForm> getPaymentGateways() {
        List<PaymentGatewayForm> paymentGatewayForms = new ArrayList<>();
        Iterable<PaymentGateway> paymentGateways = this.paymentGatewayRepository.findAll();
        for(PaymentGateway paymentGateway: paymentGateways){
            PaymentGatewayForm paymentGatewayForm = new PaymentGatewayForm();

            paymentGatewayForm.setPaymentGatewayUUID(paymentGateway.getUUID());
            paymentGatewayForm.setName(paymentGateway.getName());
            paymentGatewayForm.setMerchantId(paymentGateway.getMerchantId());
            paymentGatewayForm.setPaymentGatewayKey(paymentGateway.getPaymentGatewayKey());
            paymentGatewayForm.setPaymentGatewaySecret(paymentGateway.getPaymentGatewaySecret());
            paymentGatewayForm.setCallbackUrl(paymentGateway.getCallbackUrl());
            paymentGatewayForm.setEnabled(paymentGateway.getEnabled());

            paymentGatewayForms.add(paymentGatewayForm);
        }

        return paymentGatewayForms;
    }

    @Override
    public PaymentGatewayForm getPaymentGateway(String paymentGatewayUUID) throws Exception {
        if(null == paymentGatewayUUID){
            throw new Exception("Payment Gateway id cannot be null...");
        }

        if("".equals(paymentGatewayUUID)){
            throw new Exception("UPayment Gateway id cannot be a empty string...");
        }

        Optional<PaymentGateway> optionalPaymentGateway = this.paymentGatewayRepository.findById(paymentGatewayUUID);

        if(optionalPaymentGateway.isPresent()){
            PaymentGateway paymentGateway = optionalPaymentGateway.get();

            PaymentGatewayForm paymentGatewayForm = new PaymentGatewayForm();
            paymentGatewayForm.setPaymentGatewayUUID(paymentGateway.getUUID());
            paymentGatewayForm.setName(paymentGateway.getName());
            paymentGatewayForm.setMerchantId(paymentGateway.getMerchantId());
            paymentGatewayForm.setPaymentGatewayKey(paymentGateway.getPaymentGatewayKey());
            paymentGatewayForm.setPaymentGatewaySecret(paymentGateway.getPaymentGatewaySecret());
            paymentGatewayForm.setCallbackUrl(paymentGateway.getCallbackUrl());

            return paymentGatewayForm;
        } else {
            throw new Exception("Invalid Payment Gateway id...");
        }
    }

    @Override
    public PaymentGatewayForm editPaymentGateway(String paymentGatewayUUID, String name, String merchantId, String paymentGatewayKey, String paymentGatewaySecret, String callbackUrl, String userFullName) throws Exception {
        if(null == paymentGatewayUUID || "".equals(paymentGatewayUUID)){
            throw new Exception("Payment Gateway id cannot be null or empty...");
        }

        if(null == name || "".equals(name)){
            throw new Exception("Payment gateway name cannot be null or empty...");
        }

        if(null == merchantId || "".equals(merchantId)){
            throw new Exception("Merchant id cannot be null or empty...");
        }

        if(null == paymentGatewayKey || "".equals(paymentGatewayKey)){
            throw new Exception("Payment gateway key cannot be null or empty...");
        }

        if(null == paymentGatewaySecret || "".equals(paymentGatewaySecret)){
            throw new Exception("Payment gateway secret cannot be null or empty...");
        }

        if(null == callbackUrl || "".equals(callbackUrl)){
            throw new Exception("Call back url cannot be null or empty...");
        }

        if(null == userFullName || "".equals(userFullName)){
            throw new Exception("User full name cannot be null or empty...");
        }

        PaymentGateway paymentGateway = this.paymentGatewayRepository.findById(paymentGatewayUUID).get();

        if(null != paymentGateway){

            paymentGateway.setName(name);
            paymentGateway.setMerchantId(merchantId);
            paymentGateway.setPaymentGatewayKey(paymentGatewayKey);
            paymentGateway.setPaymentGatewaySecret(paymentGatewaySecret);
            paymentGateway.setCallbackUrl(callbackUrl);

            paymentGateway = this.paymentGatewayRepository.save(paymentGateway);

            PaymentGatewayForm paymentGatewayForm = new PaymentGatewayForm();
            paymentGatewayForm.setPaymentGatewayUUID(paymentGateway.getUUID());
            paymentGatewayForm.setName(paymentGateway.getName());
            paymentGatewayForm.setMerchantId(paymentGateway.getMerchantId());
            paymentGatewayForm.setPaymentGatewayKey(paymentGateway.getPaymentGatewayKey());
            paymentGatewayForm.setPaymentGatewaySecret(paymentGateway.getPaymentGatewaySecret());
            paymentGatewayForm.setCallbackUrl(paymentGateway.getCallbackUrl());

            return paymentGatewayForm;

        } else {
            throw new Exception("Invalid payment gateway id...");
        }
    }

    @Override
    public void deletePaymentGateway(String paymentGatewayUUID) throws Exception {
        if(null == paymentGatewayUUID){
            throw new Exception("Payment Gateway id cannot be null...");
        }

        if("".equals(paymentGatewayUUID)){
            throw new Exception("Payment Gateway id cannot be a empty string...");
        }

        this.paymentGatewayRepository.deleteById(paymentGatewayUUID);
    }

    @Override
    public String enablePaymentGateway(String paymentGatewayId) throws Exception {
        if(null == paymentGatewayId){
            throw new Exception("Payment Gateway id cannot be null...");
        }

        if("".equals(paymentGatewayId)){
            throw new Exception("Payment Gateway id cannot be a empty string...");
        }

        Optional<PaymentGateway> optionalPaymentGateway = this.paymentGatewayRepository.findById(paymentGatewayId);

        if(optionalPaymentGateway.isPresent()){
            PaymentGateway paymentGateway = optionalPaymentGateway.get();
            paymentGateway.setEnabled(1);
            this.paymentGatewayRepository.save(paymentGateway);
            this.logger.info(String.format("the payment gateway [ %s ] is now enabled for use...", paymentGateway.getName()));
            return "1";
        } else {
            this.logger.info(String.format("the payment gateway with id [ %s ] is does not exist...", paymentGatewayId));
            return "0";
        }
    }

    @Override
    public void disablePaymentGateway(String paymentGatewayId) throws Exception {
        if(null == paymentGatewayId){
            throw new Exception("Payment Gateway id cannot be null...");
        }

        if("".equals(paymentGatewayId)){
            throw new Exception("Payment Gateway id cannot be a empty string...");
        }

        Optional<PaymentGateway> optionalPaymentGateway = this.paymentGatewayRepository.findById(paymentGatewayId);

        if(optionalPaymentGateway.isPresent()){
            PaymentGateway paymentGateway = optionalPaymentGateway.get();
            paymentGateway.setEnabled(0);
            this.paymentGatewayRepository.save(paymentGateway);
            this.logger.info(String.format("the payment gateway [ %s ] is now disabled...", paymentGateway.getName()));
        } else {
            this.logger.info(String.format("the payment gateway with id [ %s ] is does not exist...", paymentGatewayId));
        }
    }
}
