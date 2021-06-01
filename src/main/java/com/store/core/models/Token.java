package com.store.core.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @Column(name = "uuid")
    private String UUID;

    @Column(name = "type")
    private String type;

    @Column(name = "expiry_time_in_hours")
    private int expiryTimeInHours;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    public Token(){
        this.setUUID(java.util.UUID.randomUUID().toString());
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExpiryTimeInHours() {
        return expiryTimeInHours;
    }

    public void setExpiryTimeInHours(int expiryTimeInHours) {
        this.expiryTimeInHours = expiryTimeInHours;
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