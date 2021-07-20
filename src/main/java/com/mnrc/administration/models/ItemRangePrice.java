package com.mnrc.administration.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item_range_price")
public class ItemRangePrice {

    @Id
    @Column(name = "UUID")
    private String UUID;

    @Column(name = "low_1")
    private int low1;

    @Column(name = "high_1")
    private int high1;

    @Column(name = "price_1")
    private String price1;

    @Column(name = "low_2")
    private int low2;

    @Column(name = "high_2")
    private int high2;

    @Column(name = "price_2")
    private String price2;

    @Column(name = "low_3")
    private int low3;

    @Column(name = "high_3")
    private int high3;

    @Column(name = "price_3")
    private String price3;

    @Column(name = "low_4")
    private int low4;

    @Column(name = "high_4")
    private int high4;

    @Column(name = "price_4")
    private String price4;

    @ManyToOne
    private Item item;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "modified_date")
    private Date modifiedDate;

    public ItemRangePrice(){
        this.setUUID(java.util.UUID.randomUUID().toString());
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public int getLow1() {
        return low1;
    }

    public void setLow1(int low1) {
        this.low1 = low1;
    }

    public int getHigh1() {
        return high1;
    }

    public void setHigh1(int high1) {
        this.high1 = high1;
    }

    public int getLow2() {
        return low2;
    }

    public void setLow2(int low2) {
        this.low2 = low2;
    }

    public int getHigh2() {
        return high2;
    }

    public void setHigh2(int high2) {
        this.high2 = high2;
    }

    public int getLow3() {
        return low3;
    }

    public void setLow3(int low3) {
        this.low3 = low3;
    }

    public int getHigh3() {
        return high3;
    }

    public void setHigh3(int high3) {
        this.high3 = high3;
    }

    public int getLow4() {
        return low4;
    }

    public void setLow4(int low4) {
        this.low4 = low4;
    }

    public int getHigh4() {
        return high4;
    }

    public void setHigh4(int high4) {
        this.high4 = high4;
    }

    public String getPrice1() {
        return price1;
    }

    public void setPrice1(String price1) {
        this.price1 = price1;
    }

    public String getPrice2() {
        return price2;
    }

    public void setPrice2(String price2) {
        this.price2 = price2;
    }

    public String getPrice3() {
        return price3;
    }

    public void setPrice3(String price3) {
        this.price3 = price3;
    }

    public String getPrice4() {
        return price4;
    }

    public void setPrice4(String price4) {
        this.price4 = price4;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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
