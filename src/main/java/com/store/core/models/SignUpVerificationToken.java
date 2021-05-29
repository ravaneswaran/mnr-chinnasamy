package com.store.core.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "token")
public class SignUpVerificationToken extends Token{

    public SignUpVerificationToken(){
       this.setType("SIGNUP-VERIFICATION-TOKEN");
       this.setExpiryTimeInHours(24);
    }
}
