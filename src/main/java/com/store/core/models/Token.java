package com.store.core.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class Token extends AbstractModel{

    @Column(name = "type")
    private String type;

    @Column(name = "expiry_time_in_hours")
    private int expiryTimeInHours;

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
}