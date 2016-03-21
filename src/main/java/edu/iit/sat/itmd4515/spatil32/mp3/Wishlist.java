/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.mp3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
@NamedQueries({
    @NamedQuery(name = "Wishlist.seeAllWishlists", query = "select w from Wishlist w"),
    @NamedQuery(name = "Wishlist.seeWishlistsByCustomerId", query = "select w from Wishlist w where w.customer.customerId = :id")
})
public class Wishlist 
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistId;
    @ManyToOne
    @JoinColumn(name = "customerId_fk")
    private Customer customer;

    @ManyToOne
    private Products product;

    @Temporal(TemporalType.DATE)
    private Date creationDate;



    public Wishlist() {
    }

    public Wishlist(int wishlistId, Date creationDate) {
        this.wishlistId = wishlistId;
        this.creationDate = creationDate;
    }

    public Wishlist(Customer customer, Date creationDate) {
        this.customer = customer;
        this.creationDate = creationDate;
    }

    public Wishlist(Customer customer, Products product, Date creationDate) {
        this.customer = customer;
        this.product = product;
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
    public Date getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Wishlist{" + "wishlistId=" + wishlistId + ", customer=" + customer + ", creationDate=" + creationDate + '}';
    }
    public Products getProduct() {
        return product;
    }
    public void setProduct(Products product) {
        this.product = product;
    }
}