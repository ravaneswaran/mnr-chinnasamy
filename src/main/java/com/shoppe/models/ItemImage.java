package com.shoppe.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "item_imageries")
public class ItemImage {

    @Id
    @Column( name = "uuid")
    private String UUID;

    @Column( name = "image")
    private byte[] image;

    @Column( name = "position")
    private int position;

    @Column( name = "created_by")
    private String createdBy;

    @Column( name = "createdDate")
    private Date createdDate;

    @Column( name = "modifiedDate")
    private Date modifiedDate;

    @ManyToOne
    private Item item;

    public ItemImage(){
        this.setUUID(java.util.UUID.randomUUID().toString());
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
