package com.mnrc.administration.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "payment_gateway")
public class PaymentGateway {

    @Id
    @Column(name = "uuid")
    private String UUID;

    @Column(name = "name")
    private String name;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "payment_gateway_logo")
    private String paymentGatewayLogo;

    @Column(name = "payment_gateway_key")
    private String paymentGatewayKey;

    @Column(name = "payment_gateway_secret")
    private String paymentGatewaySecret;

    @Column(name = "enabled")
    private int enabled;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    public PaymentGateway(){
        this.setUUID(java.util.UUID.randomUUID().toString());
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }
}
