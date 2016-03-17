/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.mp3;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Dell
 */
@Entity
@Table(name = "spatil32_Wishlist")
public class Wishlist 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistId;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId_fk")
    private int customerId;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "productId_fk")
    private int productId;
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    public Wishlist() {
    }

    public Wishlist(int wishlistId, Date creationDate) {
        this.wishlistId = wishlistId;
        this.creationDate = creationDate;
    }

    public Wishlist(int wishlistId, int customerId, int productId, Date creationDate) {
        this.wishlistId = wishlistId;
        this.customerId = customerId;
        this.productId = productId;
        this.creationDate = creationDate;
    }
    

    /**
     * Get the value of wishlistId
     *
     * @return the value of wishlistId
     */
    public int getWishlistId() {
        return wishlistId;
    }



    /**
     * Set the value of wishlistId
     *
     * @param wishlistId new value of wishlistId
     */
    public void setWishlistId(int wishlistId) {
        this.wishlistId = wishlistId;
    }
    public int getCustomerId() {
        return customerId;
    }
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }
    public int getProductId() {
        return productId;
    }
    public void setProductId(int productId) {
        this.productId = productId;
    }
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "Wishlist{" + "wishlistId=" + wishlistId + ", customerId=" + customerId + ", productId=" + productId + ", creationDate=" + creationDate + '}';
    }
}