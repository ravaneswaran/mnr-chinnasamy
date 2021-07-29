package com.mnrc.administration.ui.forms;

public class PaymentGatewayForm {

    private String paymentGatewayUUID;

    private String name;

    private String merchantId;

    private String paymentGatewayLogo;

    private String paymentGatewayKey;

    private String paymentGatewaySecret;

    private String enabled;

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

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public String getPaymentGatewayUUID() {
        return paymentGatewayUUID;
    }

    public void setPaymentGatewayUUID(String paymentGatewayUUID) {
        this.paymentGatewayUUID = paymentGatewayUUID;
    }
}
