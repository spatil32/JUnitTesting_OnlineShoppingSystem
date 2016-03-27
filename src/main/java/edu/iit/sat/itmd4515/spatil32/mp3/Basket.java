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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Basket POJO consist of all data fields to be persisted, constructors,
 * getters, setters and toString() method. It also contains JPA mappings and
 * persistence annotations to persist table in database.
 *
 * @author Dell
 */
@Entity
@Table(name = "spatil32_Basket")
@NamedQueries({
    @NamedQuery(name = "Basket.seeAllCustomersBaskets", query = "select b from Basket b"),
    @NamedQuery(name = "Basket.findBasketByCustomerId", query = "select b from Basket b where b.customer.customerId = :id")
})

public class Basket {

    //primary key of table
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int basketId;

    @Temporal(TemporalType.DATE)
    private Date shoppingDate;
    private int numberOfItems;
    private int pricePerUnit;

    //foreign key constraint 
    @OneToOne
    @JoinColumn(name = "customerId_fk")
    private Customer customer;

    //extra table for manytomany mapping
    @ManyToMany
    @JoinTable(
            name = "spatil32_basket_products",
            joinColumns = @JoinColumn(name = "BASKET_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private List<Products> products = new ArrayList<>();

    /**
     * parameterless constructor
     */
    public Basket() {
    }

    /**
     *
     * @param basketId contains unique basket id
     * @param shoppingDate contains shopping date
     * @param numberOfItems contains number of items in basket
     * @param pricePerUnit contains price per unit of item
     */
    public Basket(int basketId, Date shoppingDate, int numberOfItems, int pricePerUnit) {
        this.basketId = basketId;
        this.shoppingDate = shoppingDate;
        this.numberOfItems = numberOfItems;
        this.pricePerUnit = pricePerUnit;
    }

    /**
     *
     * @param shoppingDate contains shopping date
     * @param numberOfItems contains number of items in basket
     * @param pricePerUnit contains price per unit
     * @param customer contains customer info related to basket
     */
    public Basket(Date shoppingDate, int numberOfItems, int pricePerUnit, Customer customer) {
        this.shoppingDate = shoppingDate;
        this.numberOfItems = numberOfItems;
        this.pricePerUnit = pricePerUnit;
        this.customer = customer;
    }

    /**
     * Get the value of basketId
     *
     * @return the value of basketId
     */
    public int getBasketId() {
        return basketId;
    }

    /**
     * Set the value of basketId
     *
     * @param basketId new value of basketId
     */
    public void setBasketId(int basketId) {
        this.basketId = basketId;
    }

    /**
     *
     * @return shopping date
     */
    public Date getShoppingDate() {
        return shoppingDate;
    }

    /**
     *
     * @param shoppingDate sets new date for shopping
     */
    public void setShoppingDate(Date shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    /**
     *
     * @return number of items in basket
     */
    public int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     *
     * @param numberOfItems sets number of items in basket
     */
    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    /**
     *
     * @return price per unit
     */
    public int getPricePerUnit() {
        return pricePerUnit;
    }

    /**
     *
     * @param pricePerUnit sets price per unit
     */
    public void setPricePerUnit(int pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    /**
     *
     * @return customer for the basket
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer sets customer to the basket
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     *
     * @return list of products in basket
     */
    public List<Products> getProducts() {
        return products;
    }

    /**
     *
     * @param products adds new products to basket
     */
    public void setProducts(List<Products> products) {
        this.products = products;
    }

    /**
     * helper method to add new product
     *
     * @param product is new product.
     */
    public void addProducts(Products product) {
        if (!this.products.contains(product)) {
            this.products.add(product);
        }
    }

    @Override
    public String toString() {
        return "Basket{" + "basketId=" + basketId + ", shoppingDate=" + shoppingDate + ", numberOfItems=" + numberOfItems + ", pricePerUnit=" + pricePerUnit + ", customer=" + customer + '}';
    }
}
