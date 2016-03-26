/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.iit.sat.itmd4515.spatil32.mp3;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Wishlist POJO consist of all data fields to be persisted, constructors, getters, setters and toString() method.
 * It also contains JPA mappings and persistence annotations to persist table in database.
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
    //primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wishlistId;
    
    //foreign key mapping
    //Many wishlists one customer
    @ManyToOne
    @JoinColumn(name = "customerId_fk")
    private Customer customer;

    //One product in many wishlist
    @ManyToOne
    private Products product;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    /**
     * parameterless constructor
     */
    public Wishlist() {
    }

    /**
     *
     * @param wishlistId sets wishlist
     * @param creationDate sets creation date
     */
    public Wishlist(int wishlistId, Date creationDate) {
        this.wishlistId = wishlistId;
        this.creationDate = creationDate;
    }

    /**
     *
     * @param customer sets new customer for wishlist
     * @param creationDate sets new date
     */
    public Wishlist(Customer customer, Date creationDate) {
        this.customer = customer;
        this.creationDate = creationDate;
    }

    /**
     * @param customer sets new customer for wishlist
     * @param product sets new product
     * @param creationDate sets new date
     */
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

    /**
     *
     * @return new creation date
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate sets new date
     */
    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    /**
     *
     * @return customer with wishlist
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer sets customer to new wishlist
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     *
     * @return product in wishlist
     */
    public Products getProduct() {
        return product;
    }

    /**
     *
     * @param product set product in wishlist
     */
    public void setProduct(Products product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Wishlist{" + "wishlistId=" + wishlistId + ", customer=" + customer + ", creationDate=" + creationDate + '}';
    }
}