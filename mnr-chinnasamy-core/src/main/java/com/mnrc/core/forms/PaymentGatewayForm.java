package com.mnrc.core.forms;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;

@Component
public class PaymentGatewayForm {

    private String paymentGatewayUUID;

    @NotEmpty(message = "Name should not be empty")
    private String name;

    @NotEmpty(message = "Merchant Id cannot be empty")
    private String merchantId;

    private String paymentGatewayLogo;

    @NotEmpty(message = "Payment gateway key cannot be empty")
    private String paymentGatewayKey;

    @NotEmpty(message = "Payment gateway secret cannot be empty")
    private String paymentGatewaySecret;

    @NotEmpty(message = "Callback URL cannot be empty")
    private String callbackUrl;

    private int enabled;

    private String action = "/payment-gateway/add";

    private String title;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getPaymentGatewayLogo() {
        return paymentGatewayLogo;
    }

    public void setPaymentGatewayLogo(String paymentGatewayLogo) {
        this.paymentGatewayLogo = paymentGatewayLogo;
    }

    public String getPaymentGatewayKey() {
        return paymentGatewayKey;
    }

    public void setPaymentGatewayKey(String paymentGatewayKey) {
        this.paymentGatewayKey = paymentGatewayKey;
    }

    public String getPaymentGatewaySecret() {
        return paymentGatewaySecret;
    }

    public void setPaymentGatewaySecret(String paymentGatewaySecret) {
        this.paymentGatewaySecret = paymentGatewaySecret;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getPaymentGatewayUUID() {
        return paymentGatewayUUID;
    }

    public void setPaymentGatewayUUID(String paymentGatewayUUID) {
        this.paymentGatewayUUID = paymentGatewayUUID;
    }

    public String getCallbackUrl() {
        return callbackUrl;
    }

    public void setCallbackUrl(String callbackUrl) {
        this.callbackUrl = callbackUrl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getTitle() {
        return String.format("Selecting this would make [ %s ] available for the application", this.getName());
    }
}
