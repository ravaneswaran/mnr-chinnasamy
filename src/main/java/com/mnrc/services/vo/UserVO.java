package com.mnrc.services.vo;

public class UserVO extends BaseVO{

    private String userUUID;
    private String firstName;
    private String middleInitial;
    private String lastName;
    private String emailId;
    private String mobileNo;
    private String uniqueId;

    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private String country;
    private String zipCode;

    public String getUserUUID() {
        return userUUID;
    }

    public void setUserUUID(String userUUID) {
        this.userUUID = userUUID;
    }

    public boolean isErroneous(){
        return !isNotErroneous();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setMiddleInitial(String middleInitial) {
        this.middleInitial = middleInitial;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getMiddleInitial() {
        return this.middleInitial;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getEmailId() {
        return this.emailId;
    }

    public String getMobileNo() {
        return this.mobileNo;
    }

    public String getUniqueId() {
        return this.uniqueId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getAddressLine3() {
        return addressLine3;
    }

    public void setAddressLine3(String addressLine3) {
        this.addressLine3 = addressLine3;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}